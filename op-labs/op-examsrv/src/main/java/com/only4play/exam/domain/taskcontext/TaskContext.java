package com.only4play.exam.domain.taskcontext;

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
import com.only4play.exam.infrastructure.converter.ContextTypeConverter;
import com.only4play.jpa.converter.ValidStatusConverter;
import com.only4play.jpa.support.BaseJpaAggregate;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.only4play.exam.domain.taskcontext.vo")
@GenCreator(pkgName = "com.only4play.exam.domain.taskcontext.creator")
@GenUpdater(pkgName = "com.only4play.exam.domain.taskcontext.updater")
@GenRepository(pkgName = "com.only4play.exam.domain.taskcontext.repository")
@GenService(pkgName = "com.only4play.exam.domain.taskcontext.service")
@GenServiceImpl(pkgName = "com.only4play.exam.domain.taskcontext.service")
@GenQuery(pkgName = "com.only4play.exam.domain.taskcontext.query")
@GenMapper(pkgName = "com.only4play.exam.domain.taskcontext.mapper")
@GenController(pkgName = "com.only4play.exam.controller")
@GenCreateRequest(pkgName = "com.only4play.exam.domain.taskcontext.request")
@GenUpdateRequest(pkgName = "com.only4play.exam.domain.taskcontext.request")
@GenQueryRequest(pkgName = "com.only4play.exam.domain.taskcontext.request")
@GenResponse(pkgName = "com.only4play.exam.domain.taskcontext.response")
@Entity
@Table(name = "taskContext")
@Data
public class TaskContext extends BaseJpaAggregate {

  @FieldDesc(name = "任务Id")
  private Long taskId;

  @FieldDesc(name = "任务组Id")
  private Long taskGroupId;

  @FieldDesc(name = "上下文类型")
  @Convert(converter = ContextTypeConverter.class)
  private ContextType contextType;

  @FieldDesc(name = "上下文JsonStr")
  private String jsonStr;

  @Convert(converter = ValidStatusConverter.class)
  @IgnoreUpdater
  @IgnoreCreator
  private ValidStatus validStatus;

  public void init() {
    setValidStatus(ValidStatus.VALID);
  }

  public void valid(){
    setValidStatus(ValidStatus.VALID);
  }

  public void invalid(){
    setValidStatus(ValidStatus.INVALID);
  }
}
