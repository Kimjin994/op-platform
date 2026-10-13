package com.only4play.exam.domain.taskgroup.domainservice;

import com.only4play.exam.domain.task.Task;
import com.only4play.exam.domain.task.TaskStatus;
import com.only4play.exam.domain.task.domainservice.ITaskDomainService;
import com.only4play.exam.domain.task.repository.TaskRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaskGroupDomainServiceImpl implements ITaskGroupDomainService{

  private final TaskRepository taskRepository;

  private final ITaskDomainService taskDomainService;


  @Override
  public void start(Long taskGroupId) {

    List<Task> taskList = taskRepository.findAllByTaskGroupId(taskGroupId);
    Optional<Task> task = taskList.stream()
        .sorted()
        .filter(t -> Objects.equals(TaskStatus.CREATE, t.getTaskStatus()))
        .findFirst();
    if(task.isPresent()){
      taskDomainService.start(task.get().getId());
    }


  }

  @Override
  public void end(Long taskGroupId) {
    List<Task> taskList = taskRepository.findAllByTaskGroupId(taskGroupId);
    Optional<Task> task = taskList.stream()
        .filter(t -> Objects.equals(TaskStatus.RUNNING, t.getTaskStatus()))
        .findFirst();
    if(task.isPresent()){
      taskDomainService.end(task.get().getId());
    }

  }
}
