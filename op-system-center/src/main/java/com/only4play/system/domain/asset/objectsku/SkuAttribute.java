package com.only4play.system.domain.asset.objectsku;

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
import lombok.Data;

@GenVo(pkgName = "com.only4play.system.domain.objectsku.vo")
@GenCreator(pkgName = "com.only4play.system.domain.objectsku.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.objectsku.updater")
@GenQuery(pkgName = "com.only4play.system.domain.objectsku.query")
@Entity
@Table(name = "sku_attribute")
@Data
public class SkuAttribute extends BaseJpaAggregate {

  @FieldDesc(name = "skuId")
  private Long skuId;

  @FieldDesc(name = "模板项id")
  private Long itemId;

  @FieldDesc(name = "编码")
  private String code;

  @FieldDesc(name = "值")
  private String value;

  @FieldDesc(name = "标签")
  private String label;

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
