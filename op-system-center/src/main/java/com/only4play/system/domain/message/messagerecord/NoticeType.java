package com.only4play.system.domain.message.messagerecord;

import com.only4play.common.constants.BaseEnum;
import java.util.Optional;

public enum NoticeType implements BaseEnum<NoticeType> {

  SMS(1, "短信"),
  EMAIL(2,"邮件"),
  DING_DING(3,"钉钉"),
  WECHAT(4,"企业微信")
  ;

  NoticeType(Integer code, String name) {
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

  public static Optional<NoticeType> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(NoticeType.class, code));
  }

}
