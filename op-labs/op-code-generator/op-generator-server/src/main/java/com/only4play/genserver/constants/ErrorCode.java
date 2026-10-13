package com.only4play.genserver.constants;

import com.only4play.common.constants.BaseEnum;
import java.util.Optional;

public enum ErrorCode implements BaseEnum<ErrorCode> {

  CONFIG_WRONG(1, "请先配置生成路径和包路径");

  ErrorCode(Integer code, String name) {
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

  public static Optional<ErrorCode> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(ErrorCode.class, code));
  }

}
