package com.only4play.exam.domain.taskgroup.domainservice;

public interface ITaskGroupDomainService {

  void start(Long taskGroupId);

  void end(Long taskGroupId);

}
