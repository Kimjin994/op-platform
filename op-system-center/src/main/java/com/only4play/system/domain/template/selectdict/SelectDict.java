package com.only4play.system.domain.template.selectdict;

import com.only4play.codegen.processor.creator.GenCreator;
import com.only4play.codegen.processor.creator.IgnoreCreator;
import com.only4play.codegen.processor.query.GenQuery;
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
import java.util.List;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.only4play.system.domain.template.selectdict.vo")
@GenCreator(pkgName = "com.only4play.system.domain.template.selectdict.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.template.selectdict.updater")
@GenQuery(pkgName = "com.only4play.system.domain.template.selectdict.query")
@Entity
@Table(name = "select_dict")
@Data
public class SelectDict extends BaseJpaAggregate {

  @FieldDesc(name = "字典编码")
  private String code;

  @FieldDesc(name = "名称")
  private String name;

  @FieldDesc(name = "描述信息")
  private String description;

  @FieldDesc(name = "字典类型")
  @Convert(converter = DictTypeConverter.class)
  @TypeConverter(toTypeFullName = "java.lang.Integer")
  private DictType dictType;

  @FieldDesc(name = "http 接口地址")
  private String httpUrl;

  @FieldDesc(name = "选择值列表")
  @Convert(converter = CodeValueListConverter.class)
  private List<CodeValue> selectValues;

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
