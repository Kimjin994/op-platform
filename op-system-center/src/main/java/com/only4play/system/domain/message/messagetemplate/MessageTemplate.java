package com.only4play.system.domain.message.messagetemplate;

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
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.only4play.system.domain.message.messagetemplate.vo")
@GenCreator(pkgName = "com.only4play.system.domain.message.messagetemplate.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.message.messagetemplate.updater")
@GenQuery(pkgName = "com.only4play.system.domain.message.messagetemplate.query")
@Table(name = "message_template")
@Data
@Entity
public class MessageTemplate extends BaseJpaAggregate {


  @FieldDesc(name = "模板编码")
  private String templateCode;

  @FieldDesc(name = "名称")
  private String name;

  @FieldDesc(name = "模板")
  @Column(columnDefinition = "text")
  private String template;

  @FieldDesc(name = "模板类型")
  @Convert(converter = TemplateTypeConverter.class)
  private TemplateType templateType;

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
