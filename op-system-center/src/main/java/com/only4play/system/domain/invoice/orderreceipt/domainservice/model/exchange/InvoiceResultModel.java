package com.only4play.system.domain.invoice.orderreceipt.domainservice.model.exchange;

import lombok.Data;

/**
 * 单张发票的开票结果。按金税申请流水确认。
 */
@Data
public class InvoiceResultModel {

  private String applyFlowNo;

  private boolean success;

  private String invoiceNo;

  private String invoiceCode;

  private Long makeTime;

  private String failReason;
}
