package com.only4play.exam.domain.task.domainservice;

public interface ITaskDomainService {

  /***
   * 任务本身启动
   * @param taskId
   */
  void start(Long taskId);

  /**
   * 任务本身结束
   * @param taskId
   */
  void end(Long taskId);

}
