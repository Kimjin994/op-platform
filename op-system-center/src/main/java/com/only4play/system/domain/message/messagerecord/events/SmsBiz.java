package com.only4play.system.domain.message.messagerecord.events;

import com.only4play.common.constants.BaseEnum;
import com.only4play.extension.executor.BizScene;
import java.util.Optional;

public enum SmsBiz implements BaseEnum<SmsBiz> ,BizScene{

  ALI(1, "ali")
  ;

  SmsBiz(Integer code, String name) {
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

  public static Optional<SmsBiz> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(SmsBiz.class, code));
  }

  @Override
  public String getBizId() {
    return this.name;
  }
}
