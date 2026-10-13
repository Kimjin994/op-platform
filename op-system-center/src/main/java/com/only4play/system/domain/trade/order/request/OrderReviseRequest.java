package com.only4play.system.domain.trade.order.request;

import com.only4play.common.model.Request;
import com.only4play.system.infrastructure.model.CodeValue;
import java.util.List;
import lombok.Data;

@Data
public class OrderReviseRequest implements Request {

  private Long flowNo;

  private List<CodeValue> attrs;

  private String reviseReason;
}
