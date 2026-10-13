package com.only4play.system.domain.message.messagerecord;

import javax.persistence.AttributeConverter;

public class MsgTypeEnumConverter implements AttributeConverter<MsgTypeEnum,Integer> {

  @Override
  public Integer convertToDatabaseColumn(MsgTypeEnum msgTypeEnum) {
    return msgTypeEnum.getCode();
  }

  @Override
  public MsgTypeEnum convertToEntityAttribute(Integer code) {
    return MsgTypeEnum.of(code).orElse(null);
  }
}
