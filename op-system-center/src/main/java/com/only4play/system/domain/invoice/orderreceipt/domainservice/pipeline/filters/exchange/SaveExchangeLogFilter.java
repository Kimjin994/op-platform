package com.only4play.system.domain.invoice.orderreceipt.domainservice.pipeline.filters.exchange;

import com.only4play.order.commons.filters.AbstractOrderFilter;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.pipeline.context.ExchangeInvoiceContext;
import com.only4play.system.domain.invoice.requestlog.InvoiceRequestLogService;
import com.only4play.system.infrastructure.facade.IInvoicePipeLineFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class SaveExchangeLogFilter extends AbstractOrderFilter<ExchangeInvoiceContext> {

  private final IInvoicePipeLineFacadeService invoicePipeLineFacadeService;

  private final InvoiceRequestLogService invoiceRequestLogService;

  @Override
  protected void handle(ExchangeInvoiceContext exchangeInvoiceContext) {
    invoiceRequestLogService.save(InvoiceRequestLogService.EXCHANGE,
        exchangeInvoiceContext.getExchangeInvoiceModel().getBatchNo(),
        exchangeInvoiceContext.getExchangeInvoiceModel());
  }
}
