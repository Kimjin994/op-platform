package com.only4play.system.domain.trade.order.events;

import com.alibaba.fastjson.JSON;
import com.only4play.security.util.SpringSecurityUtils;
import com.only4play.system.domain.trade.order.OrderBase;
import com.only4play.system.domain.trade.order.domainservice.model.OrderItemModel;
import com.only4play.system.domain.trade.order.events.OrderEvents.OrderCancelEvent;
import com.only4play.system.domain.trade.order.events.OrderEvents.OrderCompleteEvent;
import com.only4play.system.domain.trade.order.events.OrderEvents.OrderCreateEvent;
import com.only4play.system.domain.trade.order.events.OrderEvents.OrderReviseEvent;
import com.only4play.system.domain.trade.reviserecord.creator.ReviseRecordCreator;
import com.only4play.system.domain.trade.reviserecord.service.IReviseRecordService;
import com.only4play.system.infrastructure.model.CodeValue;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import com.only4play.system.domain.trade.order.mapper.OrderBaseMapper;
import com.only4play.system.domain.trade.orderitem.creator.OrderItemCreator;
import com.only4play.system.domain.trade.orderitem.mapper.OrderItemMapper;
import com.only4play.system.domain.trade.orderitem.service.IOrderItemService;
import com.only4play.system.domain.trade.orderlifecycle.OrderOperateType;
import com.only4play.system.domain.trade.orderlifecycle.creator.OrderLifecycleCreator;
import com.only4play.system.domain.trade.orderlifecycle.service.IOrderLifecycleService;
import com.only4play.system.infrastructure.elastic.OrderDocument;
import com.only4play.system.infrastructure.elastic.OrderDocumentRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventProcessor {

  private final IOrderItemService orderItemService;

  private final IOrderLifecycleService orderLifecycleService;

  private final OrderDocumentRepository orderDocumentRepository;

  private final IReviseRecordService reviseRecordService;

  @EventListener
  public void handleOrderCreateForEs(OrderCreateEvent createEvent){
    OrderBase orderBase = createEvent.getOrderBase();
    OrderDocument document = OrderBaseMapper.INSTANCE.entity2Document(orderBase);
    document.setId(String.valueOf(orderBase.getFlowNo()));
    document.setOrderStatusTxt(orderBase.getOrderState().getName());
    document.setOrderTypeTxt(orderBase.getOrderType().getName());
    document.setOrderItems(createEvent.getCreateModel().getItemInfoList());
    orderDocumentRepository.save(document);
  }

  @EventListener
  public void handleOrderRevise(OrderReviseEvent event) {
    OrderBase orderBase = event.getOrderBase();
    Javers javers = JaversBuilder.javers().build();
    String diff = javers.compareCollections(event.getBeforeAttrs(), event.getAfterAttrs(), CodeValue.class)
        .prettyPrint();
    ReviseRecordCreator creator = new ReviseRecordCreator();
    creator.setOperateUser(event.getOperateUser());
    creator.setFlowNo(orderBase.getFlowNo());
    creator.setReviseBefore(JSON.toJSONString(event.getBeforeAttrs()));
    creator.setReviseAfter(JSON.toJSONString(event.getAfterAttrs()));
    creator.setDiff(diff);
    creator.setTableName("order_base");
    creator.setReviseReason(event.getReviseReason());
    reviseRecordService.createReviseRecord(creator);
    syncOrderDocument(orderBase);
    writeLifecycle(orderBase.getFlowNo(), OrderOperateType.REVISE, event.getReviseReason());
  }

  @EventListener
  public void handleOrderCancelForEs(OrderCancelEvent orderCancelEvent){
    OrderBase orderBase = orderCancelEvent.getOrderBase();
    syncOrderDocument(orderBase);
    writeLifecycle(orderBase.getFlowNo(), OrderOperateType.ORDER_CANCEL, "订单取消");
  }

  @EventListener
  public void handleOrderComplete(OrderCompleteEvent completeEvent){
    OrderBase orderBase = completeEvent.getOrderBase();
    syncOrderDocument(orderBase);
    writeLifecycle(orderBase.getFlowNo(), OrderOperateType.PAY_SUCCESS, "订单支付完成");
  }

  private void syncOrderDocument(OrderBase orderBase) {
    Optional<OrderDocument> document = orderDocumentRepository.findById(
        String.valueOf(orderBase.getFlowNo()));
    if (!document.isPresent()) {
      log.warn("ES 中没有订单文档, flowNo={}", orderBase.getFlowNo());
      return;
    }
    OrderDocument orderDocument = document.get();
    orderDocument.setOrderStatus(orderBase.getOrderState().getCode());
    orderDocument.setOrderStatusTxt(orderBase.getOrderState().getName());
    orderDocument.setPayTime(orderBase.getPayTime());
    orderDocument.setPayList(orderBase.getPayList());
    orderDocument.setAttrs(orderBase.getAttrs());
    orderDocumentRepository.save(orderDocument);
  }

  private void writeLifecycle(Long flowNo, OrderOperateType operateType, String remark) {
    OrderLifecycleCreator creator = new OrderLifecycleCreator();
    creator.setFlowNo(flowNo);
    creator.setOperateType(operateType);
    creator.setOperateUser(SpringSecurityUtils.getCurrentUsername());
    creator.setRemark(remark);
    orderLifecycleService.createOrderLifecycle(creator);
  }

  @EventListener
  public void handleOrderCreateForItem(OrderCreateEvent orderCreateEvent){
    List<OrderItemModel> itemInfoList = orderCreateEvent.getCreateModel().getItemInfoList();
    itemInfoList.stream().forEach(model -> {
      OrderItemCreator creator = OrderItemMapper.INSTANCE.model2Creator(model);
      creator.setOrderId(orderCreateEvent.getOrderBase().getId());
      creator.setFlowNo(orderCreateEvent.getOrderBase().getFlowNo());
      orderItemService.createOrderItem(creator);
    });
  }

  @EventListener
  public void handleOrderCreateForLifecycle(OrderCreateEvent createEvent){
    OrderLifecycleCreator creator = new OrderLifecycleCreator();
    creator.setFlowNo(createEvent.getOrderBase().getFlowNo());
    creator.setOperateType(OrderOperateType.ORDER_CREATE);
    creator.setOperateUser(createEvent.getCreateModel().getOperateUser());
    creator.setRemark("订单创建成功");
    orderLifecycleService.createOrderLifecycle(creator);
  }


}
