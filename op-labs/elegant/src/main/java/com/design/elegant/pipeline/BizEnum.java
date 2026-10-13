package com.design.elegant.pipeline;

import com.only4play.common.constants.BaseEnum;
import java.util.Optional;

public enum BizEnum implements BaseEnum<BizEnum> {

  TRAFFIC_EVENT(1, "业务1"),
  METRIC_EVENT(2,"业务2"),
  SIGNAL_EVENT(3,"业务3")
  ;

  BizEnum(Integer code, String name) {
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

  public static Optional<BizEnum> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(BizEnum.class, code));
  }

}
