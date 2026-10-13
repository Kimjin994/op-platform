package com.only4play.system.domain.template.templateitem;

import com.only4play.codegen.processor.creator.GenCreator;
import com.only4play.codegen.processor.creator.IgnoreCreator;
import com.only4play.codegen.processor.query.GenQuery;
import com.only4play.codegen.processor.query.QueryItem;
import com.only4play.codegen.processor.updater.GenUpdater;
import com.only4play.codegen.processor.updater.IgnoreUpdater;
import com.only4play.codegen.processor.vo.GenVo;
import com.only4play.common.annotation.FieldDesc;
import com.only4play.common.annotation.TypeConverter;
import com.only4play.common.constants.ValidStatus;
import com.only4play.jpa.converter.ValidStatusConverter;
import com.only4play.jpa.support.BaseJpaAggregate;
import com.only4play.system.infrastructure.converter.CodeValueListConverter;
import com.only4play.system.infrastructure.model.CodeValue;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@GenVo(pkgName = "com.only4play.system.domain.template.templateitem.vo")
@GenCreator(pkgName = "com.only4play.system.domain.template.templateitem.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.template.templateitem.updater")
@GenQuery(pkgName = "com.only4play.system.domain.template.templateitem.query")
@Entity
@Table(name = "template_item")
@Data
public class TemplateItem extends BaseJpaAggregate {

  @QueryItem
  @NotEmpty(message = "模板项名称不能为空")
  @FieldDesc(name = "名称")
  private String name;

  @FieldDesc(name = "输入类型")
  @Convert(converter = InputTypeConverter.class)
  @TypeConverter(toTypeFullName = "java.lang.Integer")
  private InputType inputType;

  @FieldDesc(name = "占位符")
  private String placeholder;

  @FieldDesc(name = "编码")
  private String code;

  @FieldDesc(name = "值类型")
  @Convert(converter = ValueTypeConverter.class)
  @TypeConverter(toTypeFullName = "java.lang.Integer")
  private ValueType valueType;

  @FieldDesc(name = "创建人")
  private String createUser;

  @FieldDesc(name = "排序号")
  private BigDecimal sortNum;

  @FieldDesc(name = "备注")
  private String remark;

  @FieldDesc(name = "是否必填")
  @Convert(converter = ValidStatusConverter.class)
  @TypeConverter(toTypeFullName = "java.lang.Integer")
  private ValidStatus requireFlag;

  @FieldDesc(name = "关联字典的id")
  private Long dictId;

  @FieldDesc(name = "开发扩展字段")
  @Convert(converter = CodeValueListConverter.class)
  @Column(columnDefinition = "text")
  private List<CodeValue> extList;

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
