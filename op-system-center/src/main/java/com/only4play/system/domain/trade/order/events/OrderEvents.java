package com.only4play.system.domain.trade.order.events;

import com.only4play.system.domain.trade.order.OrderBase;
import com.only4play.system.domain.trade.order.domainservice.model.OrderCompleteModel;
import com.only4play.system.domain.trade.order.domainservice.model.OrderCreateModel;
import com.only4play.system.infrastructure.model.CodeValue;
import java.util.List;
import lombok.Value;

public interface OrderEvents {

  /**
   * 订单创建事件
   */
  @Value
  class OrderCreateEvent {

    private OrderBase orderBase;
    private OrderCreateModel createModel;
  }

  /**
   * 订单完成事件
   */
  @Value
  class OrderCompleteEvent {

    private OrderBase orderBase;
    private OrderCompleteModel completeModel;
  }

  /**
   * 订单修订事件
   */
  @Value
  class OrderReviseEvent {

    private OrderBase orderBase;
    private List<CodeValue> beforeAttrs;
    private List<CodeValue> afterAttrs;
    private String reviseReason;
    private String operateUser;
  }

  /**
   * 订单取消事件
   */
  @Value
  class OrderCancelEvent {

    private OrderBase orderBase;
  }


}
