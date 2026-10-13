package com.only4play.system.domain.template.templateitem;

import javax.persistence.AttributeConverter;

public class ValueTypeConverter implements AttributeConverter<ValueType,Integer> {

  @Override
  public Integer convertToDatabaseColumn(ValueType valueType) {
    return valueType.getCode();
  }

  @Override
  public ValueType convertToEntityAttribute(Integer code) {
    return ValueType.of(code).orElse(null);
  }
}
