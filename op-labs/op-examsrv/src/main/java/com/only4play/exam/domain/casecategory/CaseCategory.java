package com.only4play.exam.domain.casecategory;

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

@GenVo(pkgName = "com.only4play.exam.domain.casecategory.vo")
@GenCreator(pkgName = "com.only4play.exam.domain.casecategory.creator")
@GenUpdater(pkgName = "com.only4play.exam.domain.casecategory.updater")
@GenRepository(pkgName = "com.only4play.exam.domain.casecategory.repository")
@GenService(pkgName = "com.only4play.exam.domain.casecategory.service")
@GenServiceImpl(pkgName = "com.only4play.exam.domain.casecategory.service")
@GenQuery(pkgName = "com.only4play.exam.domain.casecategory.query")
@GenMapper(pkgName = "com.only4play.exam.domain.casecategory.mapper")
@GenController(pkgName = "com.only4play.exam.controller")
@GenCreateRequest(pkgName = "com.only4play.exam.domain.casecategory.request")
@GenUpdateRequest(pkgName = "com.only4play.exam.domain.casecategory.request")
@GenQueryRequest(pkgName = "com.only4play.exam.domain.casecategory.request")
@GenResponse(pkgName = "com.only4play.exam.domain.casecategory.response")
@Entity
@Table(name = "case_category")
@Data
public class CaseCategory extends BaseJpaAggregate {

  @FieldDesc(name = "名称")
  private String name;

  @FieldDesc(name = "编码")
  private String code;

  @FieldDesc(name = "父Id")
  private Long pid;

  @FieldDesc(name = "等级")
  private String level;


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
