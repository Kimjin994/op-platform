package com.only4play.system.domain.invoice.orderreceipt.request;

import com.only4play.common.model.Request;
import com.only4play.order.commons.pay.PayItem;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.register.OrderRegisterModel.OrderRegisterItem;
import com.only4play.system.infrastructure.model.CodeValue;
import java.util.List;
import lombok.Data;

@Data
public class OrderRegisterRequest implements Request {

  private String tradeTypeCode;

  private String bizSystemCode;

  private String orderFlowNo;

  private List<CodeValue> orderAttrs;

  private List<PayItem> payItemList;

  private String accountId;

  private Integer accountType;

  private List<OrderRegisterItem> orderItems;
}
