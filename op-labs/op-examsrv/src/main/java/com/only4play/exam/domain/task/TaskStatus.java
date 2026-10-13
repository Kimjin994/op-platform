package com.only4play.exam.domain.task;

import com.only4play.common.constants.BaseEnum;
import java.util.Optional;

public enum TaskStatus implements BaseEnum<TaskStatus> {

  CREATE(1, "创建成功"),
  RUNNING(2,"正在运行"),
  END(3,"结束")
  ;

  TaskStatus(Integer code, String name) {
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

  public static Optional<TaskStatus> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(TaskStatus.class, code));
  }

}
