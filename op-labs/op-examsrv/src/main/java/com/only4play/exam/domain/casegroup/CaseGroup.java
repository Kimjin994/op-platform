package com.only4play.exam.domain.casegroup;

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

@GenVo(pkgName = "com.only4play.exam.domain.casegroup.vo")
@GenCreator(pkgName = "com.only4play.exam.domain.casegroup.creator")
@GenUpdater(pkgName = "com.only4play.exam.domain.casegroup.updater")
@GenRepository(pkgName = "com.only4play.exam.domain.casegroup.repository")
@GenService(pkgName = "com.only4play.exam.domain.casegroup.service")
@GenServiceImpl(pkgName = "com.only4play.exam.domain.casegroup.service")
@GenQuery(pkgName = "com.only4play.exam.domain.casegroup.query")
@GenMapper(pkgName = "com.only4play.exam.domain.casegroup.mapper")
@GenController(pkgName = "com.only4play.exam.controller")
@GenCreateRequest(pkgName = "com.only4play.exam.domain.casegroup.request")
@GenUpdateRequest(pkgName = "com.only4play.exam.domain.casegroup.request")
@GenQueryRequest(pkgName = "com.only4play.exam.domain.casegroup.request")
@GenResponse(pkgName = "com.only4play.exam.domain.casegroup.response")
@Entity
@Table(name = "case_group")
@Data
public class CaseGroup extends BaseJpaAggregate {

  @FieldDesc(name = "组名称")
  private String groupName;

  @FieldDesc(name = "唯一编码")
  private String uuid;

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
