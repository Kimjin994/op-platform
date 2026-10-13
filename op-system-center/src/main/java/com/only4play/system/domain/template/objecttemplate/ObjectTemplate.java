package com.only4play.system.domain.template.objecttemplate;

import com.only4play.codegen.processor.creator.GenCreator;
import com.only4play.codegen.processor.creator.IgnoreCreator;
import com.only4play.codegen.processor.query.GenQuery;
import com.only4play.codegen.processor.updater.GenUpdater;
import com.only4play.codegen.processor.updater.IgnoreUpdater;
import com.only4play.codegen.processor.vo.GenVo;
import com.only4play.common.annotation.FieldDesc;
import com.only4play.common.constants.ValidStatus;
import com.only4play.jpa.converter.ValidStatusConverter;
import com.only4play.jpa.support.BaseJpaAggregate;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@GenVo(pkgName = "com.only4play.system.domain.template.objecttemplate.vo")
@GenCreator(pkgName = "com.only4play.system.domain.template.objecttemplate.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.template.objecttemplate.updater")
@GenQuery(pkgName = "com.only4play.system.domain.template.objecttemplate.query")
@Entity
@Table(name = "object_template")
@Data
public class ObjectTemplate extends BaseJpaAggregate {

  @FieldDesc(name = "模板名称")
  @NotEmpty(message = "模板名称不能为空")
  private String name;

  @FieldDesc(name = "模板编码")
  @NotEmpty(message = "模板编码不能为空")
  private String code;

  @FieldDesc(name = "创建人")
  private String createUser;

  @FieldDesc(name = "模板code")
  private String categoryCode;

  @FieldDesc(name = "模板id")
  private Long categoryId;

  @FieldDesc(name = "描述信息")
  private String description;

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
