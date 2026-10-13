package com.only4play.system.domain.invoice.invoice;

import javax.persistence.AttributeConverter;

public class InvoiceStatusConverter implements AttributeConverter<InvoiceStatus, Integer> {

  @Override
  public Integer convertToDatabaseColumn(InvoiceStatus status) {
    return status == null ? null : status.getCode();
  }

  @Override
  public InvoiceStatus convertToEntityAttribute(Integer code) {
    return code == null ? null : InvoiceStatus.of(code).orElse(null);
  }
}
