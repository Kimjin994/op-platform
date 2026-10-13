package com.only4play.system.infrastructure.constants;

import com.only4play.common.constants.BaseEnum;
import java.util.Optional;

public enum MessageErrorCode implements BaseEnum<MessageErrorCode> {

  TEMPLATE_NOT_FIND(1601001, "模板不存在"),
  MESSAGE_SEND_FAST(1601002, "消息发送过于频繁"),
  ;

  MessageErrorCode(Integer code, String name) {
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

  public static Optional<MessageErrorCode> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(MessageErrorCode.class, code));
  }

}
