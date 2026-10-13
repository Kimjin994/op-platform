package com.only4play.system.domain.invoice.orderreceipt.request;

import com.only4play.common.model.Request;
import com.only4play.system.domain.invoice.invoice.TitleType;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.InvoiceStyle;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.InvoiceType;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.exchange.CustomerModel;
import com.only4play.system.infrastructure.model.CodeValue;
import java.util.List;
import lombok.Data;

@Data
public class ExchangeInvoiceRequest implements Request {

  private InvoiceStyle invoiceStyle;

  private InvoiceType invoiceType;

  private TitleType titleType;

  private String applyChannelCode;

  private CustomerModel customerModel;

  /**
   * 注册流水号列表
   */
  private List<Long> flowNos;

  private List<CodeValue> extAttrs;
}
