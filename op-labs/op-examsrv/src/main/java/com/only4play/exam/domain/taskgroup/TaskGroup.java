package com.only4play.exam.domain.taskgroup;

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
import com.only4play.common.constants.ValidStatus;
import com.only4play.exam.domain.task.TaskStatus;
import com.only4play.exam.infrastructure.converter.TaskResultConverter;
import com.only4play.exam.infrastructure.converter.TaskStatusConverter;
import com.only4play.jpa.converter.ValidStatusConverter;
import com.only4play.jpa.support.BaseJpaAggregate;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.only4play.exam.domain.taskgroup.vo")
@GenCreator(pkgName = "com.only4play.exam.domain.taskgroup.creator")
@GenUpdater(pkgName = "com.only4play.exam.domain.taskgroup.updater")
@GenRepository(pkgName = "com.only4play.exam.domain.taskgroup.repository")
@GenService(pkgName = "com.only4play.exam.domain.taskgroup.service")
@GenServiceImpl(pkgName = "com.only4play.exam.domain.taskgroup.service")
@GenQuery(pkgName = "com.only4play.exam.domain.taskgroup.query")
@GenMapper(pkgName = "com.only4play.exam.domain.taskgroup.mapper")
@GenController(pkgName = "com.only4play.exam.controller")
@GenCreateRequest(pkgName = "com.only4play.exam.domain.taskgroup.request")
@GenUpdateRequest(pkgName = "com.only4play.exam.domain.taskgroup.request")
@GenQueryRequest(pkgName = "com.only4play.exam.domain.taskgroup.request")
@GenResponse(pkgName = "com.only4play.exam.domain.taskgroup.response")
@Entity
@Table(name = "task_group")
@Data
public class TaskGroup extends BaseJpaAggregate {

  @FieldDesc(name = "caseGroupId")
  private Long caseGroupId;

  @FieldDesc(name = "任务组名称")
  private String name;

  @FieldDesc(name = "批次号")
  private String batchNo;

  @FieldDesc(name = "任务开始时间")
  private Long startTime;

  @FieldDesc(name = "结束时间")
  private Long endTime;

  @FieldDesc(name = "任务状态")
  @Convert(converter = TaskStatusConverter.class)
  @IgnoreUpdater
  @IgnoreCreator
  private TaskStatus taskStatus;


  @FieldDesc(name = "通过数")
  private Integer passCount;

  @FieldDesc(name = "失败数")
  private Integer failCount;

  @Convert(converter = TaskResultConverter.class)
  @IgnoreUpdater
  @IgnoreCreator
  private TaskResult taskResult;

  @Convert(converter = ValidStatusConverter.class)
  @IgnoreUpdater
  @IgnoreCreator
  private ValidStatus validStatus;

  public void init() {
    setValidStatus(ValidStatus.VALID);
    setTaskStatus(TaskStatus.CREATE);
    setTaskResult(TaskResult.NONE);
    setPassCount(0);
    setFailCount(0);
  }

  public void complete(){

  }

  public void valid(){
    setValidStatus(ValidStatus.VALID);
  }

  public void invalid(){
    setValidStatus(ValidStatus.INVALID);
  }
}
