package com.only4play.system.domain.trade.order.request;

import com.only4play.common.model.Request;
import com.only4play.order.commons.pay.PayItem;
import com.only4play.system.domain.trade.order.OrderType;
import com.only4play.system.domain.trade.order.domainservice.model.OrderItemModel;
import com.only4play.system.domain.user.AccountType;
import com.only4play.system.infrastructure.model.CodeValue;
import java.util.List;
import lombok.Data;

@Data
public class OrderCreateRequest implements Request {

  private Long accountId;

  private AccountType accountType;

  private OrderType orderType;

  private List<PayItem> payList;

  private List<CodeValue> attrs;

  private List<OrderItemModel> itemInfoList;
}
