package com.only4play.exam.infrastructure.converter;

import com.only4play.exam.domain.task.TaskStatus;
import com.only4play.exam.domain.taskgroup.TaskResult;
import java.util.Objects;

public class CustomMapper {

  public TaskStatus int2TaskStatus(Integer code) {
    if (Objects.isNull(code)) {
      return null;
    }
    return TaskStatus.of(code).orElse(null);
  }

  public Integer taskStatus2Int(TaskStatus taskStatus) {
    return taskStatus.getCode();
  }

  public TaskResult int2TaskResult(Integer code) {
    if (Objects.isNull(code)) {
      return null;
    }
    return TaskResult.of(code).orElse(null);
  }

  public Integer taskResult2Int(TaskResult taskResult) {
    return taskResult.getCode();
  }

}
