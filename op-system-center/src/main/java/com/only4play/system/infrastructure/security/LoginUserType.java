package com.only4play.system.infrastructure.security;

import com.only4play.common.constants.BaseEnum;
import java.util.Optional;

public enum LoginUserType implements BaseEnum<LoginUserType> {

  ADMIN_USER(1, "后台用户"),
  CUSTOMER(2,"前端用户")
  ;

  LoginUserType(Integer code, String name) {
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

  public static Optional<LoginUserType> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(LoginUserType.class, code));
  }

}
