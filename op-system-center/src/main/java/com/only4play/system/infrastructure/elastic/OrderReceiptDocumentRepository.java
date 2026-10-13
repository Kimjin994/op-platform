package com.only4play.system.infrastructure.elastic;

import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrderReceiptDocumentRepository extends ElasticsearchRepository<OrderReceiptDocument,String> {

  List<OrderReceiptDocument> findByTradeTypeCodeAndPhoneAndReceiptStatus(
      String tradeTypeCode, String phone, Integer receiptStatus);
}
