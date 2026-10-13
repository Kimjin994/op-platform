package com.only4play.exam.infrastructure.converter;

import com.only4play.exam.domain.taskcontext.ContextType;
import javax.persistence.AttributeConverter;

public class ContextTypeConverter implements AttributeConverter<ContextType,Integer> {

  @Override
  public Integer convertToDatabaseColumn(ContextType contextType) {
    return contextType.getCode();
  }

  @Override
  public ContextType convertToEntityAttribute(Integer code) {
    return ContextType.of(code).orElse(null);
  }
}
