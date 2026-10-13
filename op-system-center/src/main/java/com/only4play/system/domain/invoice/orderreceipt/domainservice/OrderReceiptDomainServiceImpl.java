package com.only4play.system.domain.invoice.orderreceipt.domainservice;

import cn.hutool.core.lang.Assert;
import com.only4play.common.constants.CodeEnum;
import com.only4play.common.exception.BusinessException;
import com.only4play.system.domain.invoice.invoice.Invoice;
import com.only4play.system.domain.invoice.invoice.InvoiceStatus;
import com.only4play.system.domain.invoice.invoice.repository.InvoiceRepository;
import com.only4play.system.domain.invoice.orderreceipt.ReceiptInvoiceRel;
import com.only4play.jpa.support.EntityOperations;
import com.only4play.system.domain.invoice.orderreceipt.OrderReceipt;
import com.only4play.order.commons.constants.BizEnum;
import com.only4play.order.commons.filters.selector.FilterSelector;
import com.only4play.order.commons.filters.selector.LocalListBasedFilterSelector;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.exchange.ExchangeConditionModel;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.exchange.ExchangeInvoiceModel;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.exchange.ExchangeInvoiceResultModel;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.exchange.InvoiceResultModel;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.register.OrderRegisterModel;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.register.OrderRegisterResultModel;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.pipeline.FilterChainPipeline;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.pipeline.context.ExchangeInvoiceContext;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.pipeline.context.OrderReceiptContext;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.pipeline.filters.exchange.InputCheckFilter;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.pipeline.filters.exchange.PopulateInvoiceFilter;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.pipeline.filters.exchange.SaveExchangeLogFilter;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.pipeline.filters.exchange.SplitAmountFilter;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.pipeline.filters.register.CalculateValidAmountFilter;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.pipeline.filters.register.EnterpriseRouterFilter;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.pipeline.filters.register.PopulateReceiptItemFilter;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.pipeline.filters.register.SaveRegisterLogFilter;
import com.only4play.system.domain.invoice.orderreceipt.repository.OrderReceiptRepository;
import com.only4play.system.domain.invoice.orderreceipt.repository.ReceiptInvoiceRelRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderReceiptDomainServiceImpl implements IOrderReceiptDomainService {

  private final FilterChainPipeline orderRegisterPipeline;

  private final FilterChainPipeline exchangeInvoicePipeline;

  private final OrderReceiptRepository orderReceiptRepository;

  private final InvoiceRepository invoiceRepository;

  private final ReceiptInvoiceRelRepository receiptInvoiceRelRepository;


  @Override
  public OrderRegisterResultModel orderRegister(OrderRegisterModel registerModel) {
    //选择要执行的filter
    FilterSelector selector = getOrderRegisterSelector();
    //构建上下文
    OrderReceiptContext orderReceiptContext = new OrderReceiptContext(BizEnum.BIZ_XXX, selector);
    //请求模型设置到上下文
    orderReceiptContext.setModel(registerModel);
    //设置一个结果bean
    OrderRegisterResultModel resultModel = new OrderRegisterResultModel();
    orderReceiptContext.setRegisterResultModel(resultModel);
    orderRegisterPipeline.getFilterChain().handle(orderReceiptContext);
    return orderReceiptContext.getRegisterResultModel();
  }

  @Override
  public boolean receiptAbandon(List<Long> ids) {
    Assert.notEmpty(ids);
    for (Long id : ids) {
      EntityOperations.doUpdate(orderReceiptRepository)
          .loadById(id)
          .update(OrderReceipt::abandon)
          .execute();
    }
    return true;
  }

  /**
   * 获取票面信息
   * @param exchangeInvoiceModel
   * @return
   */
  @Override
  public ExchangeInvoiceResultModel exchangeInvoice(
      ExchangeInvoiceModel exchangeInvoiceModel) {
    FilterSelector selector = getExchangeSelector();
    ExchangeInvoiceContext invoiceContext = new ExchangeInvoiceContext(BizEnum.BIZ_XXX, selector);
    invoiceContext.setExchangeInvoiceModel(exchangeInvoiceModel);
    ExchangeInvoiceResultModel resultModel = new ExchangeInvoiceResultModel();
    invoiceContext.setResultModel(resultModel);
    exchangeInvoicePipeline.getFilterChain().handle(invoiceContext);
    return invoiceContext.getResultModel();
  }

  @Override
  public ExchangeInvoiceResultModel exchangeInvoiceByCondition(
      ExchangeConditionModel conditionModel) {
    return null;
  }

  @Override
  public void confirmInvoices(List<InvoiceResultModel> results) {
    Assert.notEmpty(results);
    for (InvoiceResultModel result : results) {
      confirmOne(result);
    }
  }

  private void confirmOne(InvoiceResultModel result) {
    Assert.notBlank(result.getApplyFlowNo());
    Invoice invoice = invoiceRepository.findByApplyFlowNo(result.getApplyFlowNo())
        .orElseThrow(() -> new BusinessException(CodeEnum.NotFindError));
    if (invoice.isTerminal()) {
      return;
    }
    if (result.isSuccess()) {
      EntityOperations.doUpdate(invoiceRepository)
          .load(() -> invoice)
          .update(e -> {
            e.complete(result.getInvoiceNo(), result.getInvoiceCode(), result.getMakeTime());
          })
          .execute();
      if (Objects.equals(InvoiceStatus.SUCCESS, invoice.getInvoiceStatus())) {
        updateRelatedReceipts(invoice.getId(), true);
      }
      return;
    }
    EntityOperations.doUpdate(invoiceRepository)
        .load(() -> invoice)
        .update(e -> {
          e.fail(result.getFailReason());
        })
        .execute();
    if (Objects.equals(InvoiceStatus.FAIL, invoice.getInvoiceStatus())) {
      updateRelatedReceipts(invoice.getId(), false);
    }
  }

  private void updateRelatedReceipts(Long invoiceId, boolean success) {
    List<ReceiptInvoiceRel> rels = receiptInvoiceRelRepository.findByInvoiceId(invoiceId);
    for (ReceiptInvoiceRel rel : rels) {
      if (rel.getRegisterFlowNo() == null) {
        continue;
      }
      orderReceiptRepository.findByRegisterFlowNo(Long.valueOf(rel.getRegisterFlowNo()))
          .ifPresent(receipt -> EntityOperations.doUpdate(orderReceiptRepository)
              .load(() -> receipt)
              .update(success ? OrderReceipt::completeInvoice : OrderReceipt::rollbackToUnmaking)
              .execute());
    }
  }


  /**
   * 配置订单注册的filter
   * @return
   */
  private FilterSelector getOrderRegisterSelector() {
    LocalListBasedFilterSelector selector = new LocalListBasedFilterSelector();
    selector.addFilter(CalculateValidAmountFilter.class.getSimpleName());
    selector.addFilter(SaveRegisterLogFilter.class.getSimpleName());
    selector.addFilter(PopulateReceiptItemFilter.class.getSimpleName());
    selector.addFilter(EnterpriseRouterFilter.class.getSimpleName());
    return selector;
  }

  /**
   * 配置开票要加载的filter
   * @return
   */
  private FilterSelector getExchangeSelector() {
    LocalListBasedFilterSelector selector = new LocalListBasedFilterSelector();
    selector.addFilter(SaveExchangeLogFilter.class.getSimpleName());
    selector.addFilter(SplitAmountFilter.class.getSimpleName());
    selector.addFilter(InputCheckFilter.class.getSimpleName());
    selector.addFilter(PopulateInvoiceFilter.class.getSimpleName());
    return selector;
  }
}
