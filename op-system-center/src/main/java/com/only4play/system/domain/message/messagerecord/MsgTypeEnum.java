package com.only4play.system.domain.message.messagerecord;

import com.only4play.common.constants.BaseEnum;
import java.util.Optional;

public enum MsgTypeEnum implements BaseEnum<MsgTypeEnum> {

  VERIFY(1, "验证码"),
  NOTICE(2,"通知");

  MsgTypeEnum(Integer code, String name) {
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

  public static Optional<MsgTypeEnum> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(MsgTypeEnum.class, code));
  }

}
