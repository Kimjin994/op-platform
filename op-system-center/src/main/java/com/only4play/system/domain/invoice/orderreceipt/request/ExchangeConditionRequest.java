package com.only4play.system.domain.invoice.orderreceipt.request;

import com.only4play.common.model.Request;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.InvoiceStyle;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.InvoiceType;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.exchange.CustomerModel;
import lombok.Data;

@Data
public class ExchangeConditionRequest implements Request {

  private String tradeType;

  private String phone;

  private InvoiceStyle invoiceStyle;

  private InvoiceType invoiceType;

  private boolean preview;

  private CustomerModel customerModel;
}
