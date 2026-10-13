package com.only4play.system.domain.invoice.invoice.request;

import com.only4play.common.model.Request;
import lombok.Data;

@Data
public class InvoiceResultRequest implements Request {

  private String applyFlowNo;

  private boolean success;

  private String invoiceNo;

  private String invoiceCode;

  private Long makeTime;

  private String failReason;
}
