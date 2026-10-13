package com.only4play.system.domain.invoice.orderreceipt.domainservice;

import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.exchange.ExchangeConditionModel;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.exchange.ExchangeInvoiceModel;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.exchange.ExchangeInvoiceResultModel;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.exchange.InvoiceResultModel;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.register.OrderRegisterModel;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.register.OrderRegisterResultModel;
import java.util.List;

/**
 * 凭据领域服务层
 */
public interface IOrderReceiptDomainService {

  /**
   * 注册凭据
   */
  OrderRegisterResultModel orderRegister(OrderRegisterModel registerModel);

  /**
   * 凭据作废
   */
  boolean receiptAbandon(List<Long> ids);

  /**
   * 开票==换取发票
   */
  ExchangeInvoiceResultModel exchangeInvoice(ExchangeInvoiceModel exchangeInvoiceModel);

  /**
   * 根据条件开票
   */
  ExchangeInvoiceResultModel exchangeInvoiceByCondition(ExchangeConditionModel conditionModel);

  /**
   * 按交易类型和手机号找出未开票凭据，组装开票请求
   */
  ExchangeInvoiceModel prepareExchange(ExchangeConditionModel conditionModel);

  /**
   * 按发票确认开票结果。成功的凭据改为已完成，失败的凭据退回未开票。
   */
  void confirmInvoices(List<InvoiceResultModel> results);

}
