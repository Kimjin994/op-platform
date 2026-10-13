package com.only4play.exam.domain.taskgroup;

import com.only4play.common.constants.BaseEnum;
import java.util.Optional;

public enum TaskResult implements BaseEnum<TaskResult> {
  NONE(0,"未测试"),
  SUCCESS(1, "成功"),
  FAIL(2,"失败")
  ;

  TaskResult(Integer code, String name) {
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

  public static Optional<TaskResult> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(TaskResult.class, code));
  }

}
