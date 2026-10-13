package com.only4play.exam.infrastructure.constants;

import com.only4play.common.constants.BaseEnum;
import java.util.Optional;

/** 认证中心
 * 12 模块名
 * 01 应用编号 01 job 02 web 03 srv
 * 001 异常编号
 */
public enum ExamErrorCode implements BaseEnum<ExamErrorCode> {

  TASK_CANT_END(1001001, "只有运行中的才可以结束"),
  TASK_CANT_START(1001002,"当前任务不可以启动")
  ;

  ExamErrorCode(Integer code, String name) {
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

  public static Optional<ExamErrorCode> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(ExamErrorCode.class, code));
  }

}
