package com.only4play.exam.domain.task.event;

import com.only4play.exam.domain.task.event.TaskEvent.TaskEndEvent;
import com.only4play.exam.domain.task.event.TaskEvent.TaskStartEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TaskEventProcessor {


  /**
   * 处理任务开始事件
   * @param startEvent
   */
  @EventListener
  public void handleStartEvent(TaskStartEvent startEvent){


  }

  /**
   * 支持事务
   * @param startEvent   save 之后调用
   */
  @EventListener
  public void handleStartEventForSms(TaskStartEvent startEvent){

  }

  @EventListener
  public void handleEndEvent(TaskEndEvent endEvent){

  }

}
