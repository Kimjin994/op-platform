package com.only4play.system.domain.trade.order;

import com.alibaba.fastjson.JSON;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.NumberUtil;
import com.only4play.codegen.processor.creator.GenCreator;
import com.only4play.codegen.processor.creator.IgnoreCreator;
import com.only4play.codegen.processor.query.GenQuery;
import com.only4play.codegen.processor.query.QueryItem;
import com.only4play.codegen.processor.updater.GenUpdater;
import com.only4play.codegen.processor.updater.IgnoreUpdater;
import com.only4play.codegen.processor.vo.GenVo;
import com.only4play.common.annotation.FieldDesc;
import com.only4play.common.annotation.TypeConverter;
import com.only4play.common.constants.ValidStatus;
import com.only4play.common.exception.BusinessException;
import com.only4play.jpa.converter.ValidStatusConverter;
import com.only4play.jpa.support.BaseJpaAggregate;
import com.only4play.order.commons.pay.PayItem;
import com.only4play.system.domain.trade.order.domainservice.model.OrderCompleteModel;
import com.only4play.system.domain.trade.order.domainservice.model.OrderCreateModel;
import com.only4play.system.domain.trade.order.domainservice.model.OrderReviseModel;
import com.only4play.system.domain.trade.order.events.OrderEvents.OrderCancelEvent;
import com.only4play.system.domain.trade.order.events.OrderEvents.OrderCompleteEvent;
import com.only4play.system.domain.trade.order.events.OrderEvents.OrderCreateEvent;
import com.only4play.system.domain.trade.order.events.OrderEvents.OrderReviseEvent;
import com.only4play.system.domain.user.AccountType;
import com.only4play.system.domain.user.AccountTypeConverter;
import com.only4play.system.infrastructure.constants.OrderErrorCode;
import com.only4play.system.infrastructure.converter.CodeValueListConverter;
import com.only4play.system.infrastructure.converter.PayItemListConverter;
import com.only4play.system.infrastructure.model.CodeValue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@GenVo(pkgName = "com.only4play.system.domain.trade.order.vo")
@GenCreator(pkgName = "com.only4play.system.domain.trade.order.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.trade.order.updater")
@GenQuery(pkgName = "com.only4play.system.domain.trade.order.query")
@Entity
@Table(name = "order_base")
@Data
public class OrderBase extends BaseJpaAggregate {

  @FieldDesc(name = "唯一流水号")
  @IgnoreUpdater
  private Long flowNo;

  @NotNull(message = "订单金额不能为空")
  @FieldDesc(name = "订单金额")
  private BigDecimal totalAmount;

  @FieldDesc(name = "账号Id")
  private Long accountId;

  @FieldDesc(name = "账号类型")
  @Convert(converter = AccountTypeConverter.class)
  private AccountType accountType;

  @FieldDesc(name = "订单类型、订单类型创建不同的状态机")
  @Convert(converter = OrderTypeConverter.class)
  @TypeConverter(toTypeFullName = "java.lang.Integer")
  @IgnoreUpdater
  @QueryItem
  private OrderType orderType;

  @FieldDesc(name = "支付详情")
  @IgnoreCreator
  @IgnoreUpdater
  @Convert(converter = PayItemListConverter.class)
  @Column(columnDefinition = "text")
  private List<PayItem> payList;

  @FieldDesc(name = "待支付金额")
  private BigDecimal waitPay;

  @IgnoreCreator
  @IgnoreUpdater
  @FieldDesc(name = "支付时间")
  private Long payTime;

  @FieldDesc(name = "订单状态")
  @Convert(converter = OrderStateConverter.class)
  @IgnoreCreator
  @IgnoreUpdater
  @TypeConverter(toTypeFullName = "java.lang.Integer")
  private OrderState orderState;

  @Convert(converter = ValidStatusConverter.class)
  @IgnoreUpdater
  @IgnoreCreator
  private ValidStatus validStatus;

  /**
   * 系统压力不大的时候可以这里放，压力大千万不要放这里，额外的表或者es都可以
   */
  @FieldDesc(name = "订单信息")
  @Convert(converter = CodeValueListConverter.class)
  private List<CodeValue> attrs;

  @FieldDesc(name = "是否开票")
  @Convert(converter = ValidStatusConverter.class)
  @IgnoreUpdater
  @IgnoreCreator
  private ValidStatus invoiceFlag;

  /**
   * 订单初始化
   * @param createModel
   */
  public void init(OrderCreateModel createModel) {
    setValidStatus(ValidStatus.VALID);
    setInvoiceFlag(ValidStatus.INVALID);
    BigDecimal total = getTotalAmount();
    //如果有虚拟资产的抵扣
    if (!IterUtil.isEmpty(createModel.getPayList())) {
      setPayList(createModel.getPayList());
      BigDecimal hasPay = payList.stream().map(p -> p.getMoney())
          .reduce(BigDecimal.ZERO, (a, b) -> NumberUtil.add(a, b));
      if (NumberUtil.isGreater(hasPay, total)) {
        throw new BusinessException(OrderErrorCode.PAY_TOO_BIG);
      }else if(NumberUtil.equals(hasPay,total)){
        setOrderState(OrderState.PAY_SUCCESS);
        setWaitPay(BigDecimal.ZERO);
      }else {
        setOrderState(OrderState.WAIT_PAY);
        setWaitPay(NumberUtil.sub(total,hasPay));
      }
    } else {
      //没有虚拟资产抵扣
      setPayList(Collections.EMPTY_LIST);
      if (NumberUtil.equals(total, BigDecimal.ZERO)) {
        setOrderState(OrderState.PAY_SUCCESS);
        setWaitPay(BigDecimal.ZERO);
      } else {
        setWaitPay(total);
        setOrderState(OrderState.WAIT_PAY);
      }
    }
    registerEvent(new OrderCreateEvent(this,createModel));
  }

  /**
   * 订单完成
   * @param completeModel
   */
  public void complete(OrderCompleteModel completeModel){
    if(!Objects.equals(OrderState.WAIT_PAY,getOrderState())){
      throw new BusinessException(OrderErrorCode.ORDER_NOT_WAIT_PAY);
    }
    if(IterUtil.isEmpty(completeModel.getPayItemList())){
      throw new BusinessException(OrderErrorCode.PAY_LIST_IS_NULL);
    }
    BigDecimal hasPay = completeModel.getPayItemList().stream().map(p -> p.getMoney())
        .reduce(BigDecimal.ZERO, (a, b) -> NumberUtil.add(a, b));
    if(!NumberUtil.equals(hasPay,getWaitPay())){
      throw new BusinessException(OrderErrorCode.PAY_AMOUNT_NOT_EQUAL_WAIT_PAY);
    }
    setPayTime(completeModel.getPayTime());
    List<PayItem> payItemList = getPayList();
    payItemList.addAll(completeModel.getPayItemList());
    setPayList(payItemList);
    setOrderState(OrderState.PAY_SUCCESS);
    registerEvent(new OrderCompleteEvent(this, completeModel));
  }

  /**
   * 取消订单
   */
  public void cancel(){
    if(!Objects.equals(OrderState.WAIT_PAY,getOrderState())){
      throw new BusinessException(OrderErrorCode.ORDER_NOT_WAIT_PAY);
    }
    setOrderState(OrderState.ABANDON);
    registerEvent(new OrderCancelEvent(this));
  }

  /**
   * 只修订订单属性。已作废、已登记开票的订单不能改。
   */
  public void revise(OrderReviseModel reviseModel) {
    if (Objects.equals(OrderState.ABANDON, getOrderState())
        || Objects.equals(ValidStatus.VALID, getInvoiceFlag())) {
      throw new BusinessException(OrderErrorCode.ORDER_NOT_ALLOW_REVISE);
    }
    List<CodeValue> before = copyAttrs(getAttrs());
    List<CodeValue> after = mergeAttrs(before, reviseModel.getAttrs());
    if (JSON.toJSONString(before).equals(JSON.toJSONString(after))) {
      return;
    }
    setAttrs(after);
    registerEvent(new OrderReviseEvent(this, before, after, reviseModel.getReviseReason(),
        reviseModel.getOperateUser()));
  }

  private List<CodeValue> copyAttrs(List<CodeValue> source) {
    List<CodeValue> result = new ArrayList<>();
    if (IterUtil.isEmpty(source)) {
      return result;
    }
    for (CodeValue item : source) {
      result.add(copyCodeValue(item));
    }
    return result;
  }

  private List<CodeValue> mergeAttrs(List<CodeValue> current, List<CodeValue> incoming) {
    Map<String, CodeValue> merged = new LinkedHashMap<>();
    for (CodeValue item : current) {
      merged.put(item.getK(), copyCodeValue(item));
    }
    if (incoming != null) {
      for (CodeValue item : incoming) {
        merged.put(item.getK(), copyCodeValue(item));
      }
    }
    return new ArrayList<>(merged.values());
  }

  private CodeValue copyCodeValue(CodeValue source) {
    CodeValue target = new CodeValue();
    target.setK(source.getK());
    target.setV(source.getV());
    target.setL(source.getL());
    return target;
  }

  public void valid() {
    setValidStatus(ValidStatus.VALID);
  }

  public void invalid() {
    setValidStatus(ValidStatus.INVALID);
  }
}
