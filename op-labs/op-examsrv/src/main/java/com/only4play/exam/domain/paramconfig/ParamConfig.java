package com.only4play.exam.domain.paramconfig;

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
import com.only4play.jpa.converter.ValidStatusConverter;
import com.only4play.jpa.support.BaseJpaAggregate;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.only4play.exam.domain.paramconfig.vo")
@GenCreator(pkgName = "com.only4play.exam.domain.paramconfig.creator")
@GenUpdater(pkgName = "com.only4play.exam.domain.paramconfig.updater")
@GenRepository(pkgName = "com.only4play.exam.domain.paramconfig.repository")
@GenService(pkgName = "com.only4play.exam.domain.paramconfig.service")
@GenServiceImpl(pkgName = "com.only4play.exam.domain.paramconfig.service")
@GenQuery(pkgName = "com.only4play.exam.domain.paramconfig.query")
@GenMapper(pkgName = "com.only4play.exam.domain.paramconfig.mapper")
@GenController(pkgName = "com.only4play.exam.controller")
@GenCreateRequest(pkgName = "com.only4play.exam.domain.paramconfig.request")
@GenUpdateRequest(pkgName = "com.only4play.exam.domain.paramconfig.request")
@GenQueryRequest(pkgName = "com.only4play.exam.domain.paramconfig.request")
@GenResponse(pkgName = "com.only4play.exam.domain.paramconfig.response")
@Entity
@Table(name = "param_config")
@Data
public class ParamConfig extends BaseJpaAggregate {

  @FieldDesc(name = "用例")
  private Long caseId;

  @FieldDesc(name = "参数名称")
  private String name;

  @FieldDesc(name = "参数编码")
  private String paramCode;

  @FieldDesc(name = "参数值")
  private String paramValue;

  @FieldDesc(name = "参数单位")
  private String paramUnit;


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
