package com.only4play.system.domain.trade.order.domainservice.model;

import com.only4play.system.infrastructure.model.CodeValue;
import java.util.List;
import lombok.Data;

/**
 * 订单修订模型。只允许修订订单属性。
 */
@Data
public class OrderReviseModel {

  private Long flowNo;

  private List<CodeValue> attrs;

  private String reviseReason;

  private String operateUser;
}
