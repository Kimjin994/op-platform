package com.only4play.system.domain.message.messagerecord;

import com.only4play.common.constants.BaseEnum;
import java.util.Optional;

public enum SendStatus implements BaseEnum<SendStatus> {

  WAIT(1, "待发送"),
  SENT(2, "已发送"),
  FAIL(3, "失败"),
  NOT_CONNECTED(4, "未接入");

  SendStatus(Integer code, String name) {
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

  public static Optional<SendStatus> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(SendStatus.class, code));
  }
}
