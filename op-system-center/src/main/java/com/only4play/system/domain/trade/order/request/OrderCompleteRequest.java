package com.only4play.system.domain.trade.order.request;

import com.only4play.common.model.Request;
import com.only4play.order.commons.pay.PayItem;
import java.util.List;
import lombok.Data;

@Data
public class OrderCompleteRequest implements Request {

  private Long flowNo;

  private List<PayItem> payItemList;

  private Long payTime;
}
