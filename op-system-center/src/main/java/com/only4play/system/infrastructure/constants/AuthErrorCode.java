package com.only4play.system.infrastructure.constants;

import com.only4play.common.constants.BaseEnum;

import java.util.Optional;

/**
 * 鉴权枚举
 * @author admin
 * @date 2022/08/31
 */
public enum AuthErrorCode implements BaseEnum<AuthErrorCode> {

  /**
   *
   */
  VERIFY_CODE_INCORRECT(1501001, "验证码错误"),
  ROLE_ID_NULL(1501002, "角色Id为空"),
  USER_ID_NULL(1501003, "用户Id为空"),
  PHONE_EXIST(1501004, "手机号已注册")
  ;

  AuthErrorCode(Integer code, String name) {
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

  public static Optional<AuthErrorCode> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(AuthErrorCode.class, code));
  }

}
