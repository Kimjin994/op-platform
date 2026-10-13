package com.only4play.system.domain.invoice.orderreceipt.response;

import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.exchange.ExchangeInvoiceResultModel;
import lombok.Data;

@Data
public class ExchangeByConditionResponse {

  private boolean preview;

  private String batchNo;

  private ExchangeInvoiceResultModel result;
}
