package com.only4play.exam.domain.taskcontext.context;

import com.only4play.common.annotation.FieldDesc;
import com.only4play.exam.domain.taskcontext.ContextType;
import lombok.Data;

@Data
public class CarContext implements Context{

  private String platNum;

  @FieldDesc(name = "唯一编码")
  private String code;

  @FieldDesc(name = "颜色")
  private String color;

  @Override
  public ContextType getContextType() {
    return ContextType.CAR;
  }
}
