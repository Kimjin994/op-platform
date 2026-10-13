package com.only4play.system.infrastructure.elastic;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.stereotype.Component;

/**
 * 文档上关闭了 createIndex，没有 ES 时应用也能启动；开启 ES 时在这里建索引和 mapping
 */
@Component
@ConditionalOnProperty(prefix = "es", name = "enabled", havingValue = "true", matchIfMissing = true)
@RequiredArgsConstructor
@Slf4j
public class ElasticsearchIndexInitializer implements ApplicationRunner {

  private static final List<Class<?>> DOCUMENTS = Arrays.asList(
      OrderDocument.class,
      OrderReceiptDocument.class,
      InvoiceDocument.class,
      ReceiptInvoiceRouterDocument.class);

  private final ElasticsearchOperations elasticsearchOperations;

  @Override
  public void run(ApplicationArguments args) {
    for (Class<?> document : DOCUMENTS) {
      IndexOperations indexOps = elasticsearchOperations.indexOps(document);
      if (!indexOps.exists()) {
        indexOps.createWithMapping();
        log.info("创建 ES 索引: {}", indexOps.getIndexCoordinates().getIndexName());
      }
    }
  }
}
