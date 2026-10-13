package com.only4play.exam.domain.task;

import com.only4play.codegen.processor.api.GenCreateRequest;
import com.only4play.codegen.processor.api.GenQueryRequest;
import com.only4play.codegen.processor.api.GenResponse;
import com.only4play.codegen.processor.api.GenUpdateRequest;
import com.only4play.codegen.processor.controller.GenController;
import com.only4play.codegen.processor.creator.GenCreator;
import com.only4play.codegen.processor.mapper.GenMapper;
import com.only4play.codegen.processor.query.GenQuery;
import com.only4play.codegen.processor.repository.GenRepository;
import com.only4play.codegen.processor.service.GenService;
import com.only4play.codegen.processor.creator.IgnoreCreator;
import com.only4play.codegen.processor.updater.IgnoreUpdater;
import com.only4play.codegen.processor.service.GenServiceImpl;
import com.only4play.codegen.processor.updater.GenUpdater;
import com.only4play.codegen.processor.vo.GenVo;
import com.only4play.common.annotation.FieldDesc;
import com.only4play.common.constants.CodeEnum;
import com.only4play.common.constants.ValidStatus;
import com.only4play.common.exception.BusinessException;
import com.only4play.exam.domain.task.event.TaskEvent;
import com.only4play.exam.domain.task.event.TaskEvent.TaskEndEvent;
import com.only4play.exam.domain.task.event.TaskEvent.TaskStartEvent;
import com.only4play.exam.domain.taskgroup.TaskResult;
import com.only4play.exam.infrastructure.constants.ExamErrorCode;
import com.only4play.exam.infrastructure.converter.TaskResultConverter;
import com.only4play.exam.infrastructure.converter.TaskStatusConverter;
import com.only4play.jpa.converter.ValidStatusConverter;
import com.only4play.jpa.support.BaseJpaAggregate;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.only4play.exam.domain.task.vo")
@GenCreator(pkgName = "com.only4play.exam.domain.task.creator")
@GenUpdater(pkgName = "com.only4play.exam.domain.task.updater")
@GenRepository(pkgName = "com.only4play.exam.domain.task.repository")
@GenService(pkgName = "com.only4play.exam.domain.task.service")
@GenServiceImpl(pkgName = "com.only4play.exam.domain.task.service")
@GenQuery(pkgName = "com.only4play.exam.domain.task.query")
@GenMapper(pkgName = "com.only4play.exam.domain.task.mapper")
@GenController(pkgName = "com.only4play.exam.controller")
@GenCreateRequest(pkgName = "com.only4play.exam.domain.task.request")
@GenUpdateRequest(pkgName = "com.only4play.exam.domain.task.request")
@GenQueryRequest(pkgName = "com.only4play.exam.domain.task.request")
@GenResponse(pkgName = "com.only4play.exam.domain.task.response")
@Entity
@Table(name = "task")
@Data
public class Task extends BaseJpaAggregate {

  @FieldDesc(name = "用例Id")
  private Long caseId;

  @FieldDesc(name = "用例组Id")
  private Long caseGroupId;

  private Long taskGroupId;

  @Convert(converter = ValidStatusConverter.class)
  @IgnoreUpdater
  @IgnoreCreator
  private ValidStatus validStatus;

  @FieldDesc(name = "任务开始时间")
  private Long startTime;

  private Long endTime;

  @FieldDesc(name = "任务状态")
  @Convert(converter = TaskStatusConverter.class)
  @IgnoreUpdater
  @IgnoreCreator
  private TaskStatus taskStatus;


  @Convert(converter = TaskResultConverter.class)
  @IgnoreUpdater
  @IgnoreCreator
  private TaskResult taskResult;

  @FieldDesc(name = "排序号")
  private Integer sortNum;

  /**
   * 任务开始
   */
  public void start(){
    if(Objects.equals(TaskStatus.CREATE,getTaskStatus())){
      throw new BusinessException(ExamErrorCode.TASK_CANT_START);
    }
    setStartTime(Instant.now().toEpochMilli());
    registerEvent(new TaskStartEvent(this));
  }


  /**
   * 任务结束
   */
  public void end(){
    if(!Objects.equals(TaskStatus.RUNNING,getTaskStatus())){
      throw new BusinessException(ExamErrorCode.TASK_CANT_END);
    }
    setTaskStatus(TaskStatus.END);
    setEndTime(Instant.now().toEpochMilli());
    registerEvent(new TaskEndEvent(this));
  }

  /**
   * 任务初始化
   */
  public void init() {
    setTaskStatus(TaskStatus.CREATE);
    setValidStatus(ValidStatus.VALID);
    setTaskResult(TaskResult.NONE);
    setSortNum(0);
  }




  public void valid(){
    setValidStatus(ValidStatus.VALID);
  }

  public void invalid(){
    setValidStatus(ValidStatus.INVALID);
  }
}
