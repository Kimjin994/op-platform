package com.only4play.system.domain.invoice.requestlog;

import com.only4play.common.annotation.FieldDesc;
import com.only4play.jpa.support.BaseJpaAggregate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

/**
 * 开票链路的原始请求，用于排查和补偿。
 */
@Entity
@Table(name = "invoice_request_log")
@Data
public class InvoiceRequestLog extends BaseJpaAggregate {

  @FieldDesc(name = "请求类型")
  private String requestType;

  @FieldDesc(name = "业务流水")
  private String bizNo;

  @Column(columnDefinition = "text")
  @FieldDesc(name = "请求体")
  private String body;
}
