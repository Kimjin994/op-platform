package com.only4play.system.domain.message.messagetemplate;

import com.only4play.common.constants.BaseEnum;
import java.util.Optional;

public enum TemplateType implements BaseEnum<TemplateType> {

  SMS(1, "短信"),
  EMAIL(2,"邮件"),
  MARKDOWN(3,"markdown")
  ;

  TemplateType(Integer code, String name) {
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

  public static Optional<TemplateType> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(TemplateType.class, code));
  }

}
