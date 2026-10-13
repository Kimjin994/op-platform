package com.only4play.exam.infrastructure.converter;

import com.only4play.exam.domain.task.TaskStatus;
import javax.persistence.AttributeConverter;

public class TaskStatusConverter implements AttributeConverter<TaskStatus,Integer> {

  @Override
  public Integer convertToDatabaseColumn(TaskStatus taskStatus) {
    return taskStatus.getCode();
  }

  @Override
  public TaskStatus convertToEntityAttribute(Integer code) {
    return TaskStatus.of(code).orElse(null);
  }
}
