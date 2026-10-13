package com.only4play.system.domain.asset.objectsku;

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
import com.only4play.system.infrastructure.converter.ListLongConverter;
import java.util.List;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.only4play.system.domain.asset.objectsku.vo")
@GenCreator(pkgName = "com.only4play.system.domain.asset.objectsku.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.asset.objectsku.updater")
@GenQuery(pkgName = "com.only4play.system.domain.asset.objectsku.query")
@Entity
@Table(name = "object_sku")
@Data
public class ObjectSku extends BaseJpaAggregate {

  @FieldDesc(name = "sku名称")
  private String skuName;

  @FieldDesc(name = "模板ID")
  private Long templateId;

  @FieldDesc(name = "编码")
  private String code;

  @FieldDesc(name = "备注")
  private String remark;

  @FieldDesc(name = "sku类型")
  @Convert(converter = SkuTypeConverter.class)
  @TypeConverter(toTypeFullName = "java.lang.Integer")
  private SkuType skuType;

  /**
   * 用于开票归类，按照国家编码目录配置
   */
  @FieldDesc(name = "税务分类编码")
  private String taxCategoryNo;

  @FieldDesc(name = "计量单位")
  private String measureUnit;

  @FieldDesc(name = "子sku ID list")
  @Convert(converter = ListLongConverter.class)
  private List<Long> children;

  @FieldDesc(name = "是否需要序列号")
  @Convert(converter = ValidStatusConverter.class)
  private ValidStatus uniqueFlag;

  @Convert(converter = ValidStatusConverter.class)
  @IgnoreUpdater
  @IgnoreCreator
  private ValidStatus validStatus;

  public void init() {
    setValidStatus(ValidStatus.VALID);
    setUniqueFlag(ValidStatus.VALID);
  }

  public void valid(){
    setValidStatus(ValidStatus.VALID);
  }

  public void invalid(){
    setValidStatus(ValidStatus.INVALID);
  }
}
