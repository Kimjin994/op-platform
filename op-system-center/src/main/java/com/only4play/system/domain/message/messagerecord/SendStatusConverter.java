package com.only4play.system.domain.message.messagerecord;

import javax.persistence.AttributeConverter;

public class SendStatusConverter implements AttributeConverter<SendStatus, Integer> {

  @Override
  public Integer convertToDatabaseColumn(SendStatus status) {
    return status == null ? null : status.getCode();
  }

  @Override
  public SendStatus convertToEntityAttribute(Integer code) {
    return code == null ? null : SendStatus.of(code).orElse(null);
  }
}
