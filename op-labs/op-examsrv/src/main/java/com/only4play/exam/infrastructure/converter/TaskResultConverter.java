package com.only4play.exam.infrastructure.converter;

import com.only4play.exam.domain.taskgroup.TaskResult;
import javax.persistence.AttributeConverter;

public class TaskResultConverter implements AttributeConverter<TaskResult,Integer> {

  @Override
  public Integer convertToDatabaseColumn(TaskResult taskResult) {
    return taskResult.getCode();
  }

  @Override
  public TaskResult convertToEntityAttribute(Integer code) {
    return TaskResult.of(code).orElse(null);
  }
}
