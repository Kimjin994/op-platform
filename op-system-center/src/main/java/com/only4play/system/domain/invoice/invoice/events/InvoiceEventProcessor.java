package com.only4play.system.domain.invoice.invoice.events;

import com.google.common.collect.Lists;
import com.only4play.system.domain.invoice.invoice.Invoice;
import com.only4play.system.domain.invoice.invoice.InvoiceItem;
import com.only4play.system.domain.invoice.invoice.InvoiceStatus;
import com.only4play.system.domain.invoice.invoice.events.InvoiceEvents.InvoiceCreateEvent;
import com.only4play.system.domain.invoice.invoice.events.InvoiceEvents.InvoiceFailEvent;
import com.only4play.system.domain.invoice.invoice.events.InvoiceEvents.InvoiceSuccessEvent;
import com.only4play.system.domain.invoice.invoice.mapper.InvoiceItemMapper;
import com.only4play.system.domain.invoice.invoice.mapper.InvoiceMapper;
import com.only4play.system.domain.invoice.invoice.repository.InvoiceItemRepository;
import com.only4play.system.domain.invoice.orderreceipt.ReceiptInvoiceRel;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.exchange.InvoiceItemModel;
import com.only4play.system.domain.invoice.orderreceipt.repository.ReceiptInvoiceRelRepository;
import com.only4play.system.infrastructure.elastic.InvoiceDocument;
import com.only4play.system.infrastructure.elastic.InvoiceDocumentRepository;
import com.only4play.system.infrastructure.elastic.OrderReceiptDocument;
import com.only4play.system.infrastructure.elastic.ReceiptInvoiceRouterDocument;
import com.only4play.system.infrastructure.elastic.ReceiptInvoiceRouterDocumentRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class InvoiceEventProcessor {

  private final InvoiceDocumentRepository invoiceDocumentRepository;
  private final ReceiptInvoiceRouterDocumentRepository routerDocumentRepository;
  private final InvoiceItemRepository invoiceItemRepository;
  private final ReceiptInvoiceRelRepository receiptInvoiceRelRepository;

  /**
   * 将票面信息存储在ES中
   */
  @EventListener
  public void handleInvoiceForEs(InvoiceCreateEvent event){
    InvoiceDocument document = InvoiceMapper.INSTANCE.entity2Document(event.getInvoice());
    document.setId(event.getInvoice().getApplyFlowNo());
    document.setInvoiceStatus(InvoiceStatus.MAKING.getCode());
    document.setItemModels(event.getInvoiceModel().getItemList());
    invoiceDocumentRepository.save(document);
  }

  /**
   * 路由信息存储在Es中,这样就能通过订单的属性找到发票
   */
  @EventListener
  public void handleInvoiceForRouter(InvoiceCreateEvent event){
    List<ReceiptInvoiceRouterDocument> docList = Lists.newArrayList();
    event.getInvoiceModel().getReceiptDocuments().forEach(receipt -> {
      ReceiptInvoiceRouterDocument routerDocument = new ReceiptInvoiceRouterDocument();
      routerDocument.setOrderAttrs(receipt.getOrderAttrs());
      routerDocument.setBatchNo(event.getExchangeInvoiceModel().getBatchNo());
      routerDocument.setRegisterFlowNo(String.valueOf(receipt.getRegisterFlowNo()));
      routerDocument.setOrderFlowNo(receipt.getOrderFlowNo());
      routerDocument.setExtAttrs(event.getInvoice().getExtAttrs());
      docList.add(routerDocument);
    });
    routerDocumentRepository.saveAll(docList);
  }

  /**
   * 存储发票项
   */
  @EventListener
  public void handleSaveInvoiceItem(InvoiceCreateEvent event){
    List<InvoiceItemModel> itemList = event.getInvoiceModel().getItemList();
    List<InvoiceItem> items = itemList.stream()
        .map(item -> {
          InvoiceItem invoiceItem = InvoiceItemMapper.INSTANCE.itemModel2InvoiceItem(item);
          invoiceItem.setApplyBatchNo(event.getExchangeInvoiceModel().getBatchNo());
          invoiceItem.setInvoiceId(event.getInvoice().getId());
          return invoiceItem;
        })
        .collect(Collectors.toList());
    invoiceItemRepository.saveAll(items);
  }

  /**
   * 保存订单发票的rel
   */
  @EventListener
  public void handleSaveReceiptInvoiceRelToDb(InvoiceCreateEvent event){
    List<OrderReceiptDocument> receiptDocuments = event.getInvoiceModel().getReceiptDocuments();
    List<ReceiptInvoiceRel> rels = receiptDocuments.stream()
        .map(ord -> {
          ReceiptInvoiceRel rel = new ReceiptInvoiceRel();
          rel.setBatchNo(event.getInvoice().getBatchNo());
          rel.setOrderFlowNo(ord.getOrderFlowNo());
          rel.setRegisterFlowNo(String.valueOf(ord.getRegisterFlowNo()));
          rel.setInvoiceId(event.getInvoice().getId());
          return rel;
        }).collect(Collectors.toList());
    receiptInvoiceRelRepository.saveAll(rels);
  }

  @EventListener
  public void handleInvoiceSuccess(InvoiceSuccessEvent event) {
    syncInvoiceDocument(event.getInvoice());
  }

  @EventListener
  public void handleInvoiceFail(InvoiceFailEvent event) {
    syncInvoiceDocument(event.getInvoice());
  }

  private void syncInvoiceDocument(Invoice invoice) {
    Optional<InvoiceDocument> document = invoiceDocumentRepository.findById(invoice.getApplyFlowNo());
    if (!document.isPresent()) {
      log.warn("ES 中没有发票文档, applyFlowNo={}", invoice.getApplyFlowNo());
      return;
    }
    InvoiceDocument invoiceDocument = document.get();
    if (invoice.getInvoiceStatus() != null) {
      invoiceDocument.setInvoiceStatus(invoice.getInvoiceStatus().getCode());
    }
    invoiceDocument.setInvoiceNo(invoice.getInvoiceNo());
    invoiceDocument.setInvoiceCode(invoice.getInvoiceCode());
    invoiceDocument.setMakeTime(invoice.getMakeTime());
    invoiceDocument.setFailReason(invoice.getFailReason());
    invoiceDocumentRepository.save(invoiceDocument);
  }

  /**
   * 通过mq 调用金税系统
   */
  @EventListener
  public void handleRemoteInvoke(InvoiceCreateEvent event){
      //
  }

  /**
   * 如果是专票  集成工作流
   */
  @EventListener
  public void handleForWorkFlow(InvoiceCreateEvent event){

  }

}
