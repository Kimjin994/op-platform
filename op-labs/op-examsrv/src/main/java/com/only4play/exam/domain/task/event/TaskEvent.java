package com.only4play.exam.domain.task.event;

import com.only4play.exam.domain.task.Task;
import lombok.Value;

public interface TaskEvent {


  @Value
  class TaskStartEvent{
    private Task task;
  }

  @Value
  class TaskEndEvent{
    private Task task;
  }

}
