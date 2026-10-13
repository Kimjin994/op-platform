package com.only4play.system.domain.template.templateitem;

import com.only4play.common.constants.BaseEnum;

import java.util.Optional;

public enum ValueType implements BaseEnum<ValueType> {

  BOOLEAN(1, "boolean值"),
  STRING(2, "字符串"),
  NUMBER(3,"数值"),
  NESTED_OBJECT(6,"嵌套对象")
  ;

  ValueType(Integer code, String name) {
    this.code = code;
    this.name = name;
  }

  private Integer code;
  private String name;

  @Override
  public Integer getCode() {
    return this.code;
  }

  @Override
  public String getName() {
    return this.name;
  }

  public static Optional<ValueType> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(ValueType.class, code));
  }

}
