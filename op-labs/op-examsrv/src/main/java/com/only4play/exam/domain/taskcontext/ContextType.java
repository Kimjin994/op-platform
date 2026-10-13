package com.only4play.exam.domain.taskcontext;

import com.only4play.common.constants.BaseEnum;
import java.util.Optional;

public enum ContextType implements BaseEnum<ContextType> {

  CAR(1, "车"),
  USER(2,"考生")
  ;

  ContextType(Integer code, String name) {
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

  public static Optional<ContextType> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(ContextType.class, code));
  }

}
