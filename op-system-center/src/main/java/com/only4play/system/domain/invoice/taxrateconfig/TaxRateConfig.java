package com.only4play.system.domain.invoice.taxrateconfig;

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
import java.math.BigDecimal;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.only4play.system.domain.invoice.taxrateconfig.vo")
@GenCreator(pkgName = "com.only4play.system.domain.invoice.taxrateconfig.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.invoice.taxrateconfig.updater")
@GenQuery(pkgName = "com.only4play.system.domain.invoice.taxrateconfig.query")
@Entity
@Table(name = "tax_rate_config")
@Data
public class TaxRateConfig extends BaseJpaAggregate {

  /**
   * 这里可以作为扩展，可以根据企业单独配置 分类和税率，对于一般企业不用配置了，
   * 大型集团可能需要配置
   * 每个企业配置独立的税收分类税率
   */
//  @FieldDesc(name = "企业id")
//  private Long enterpriseId;

  @FieldDesc(name = "税率")
  private BigDecimal taxRate;

  @FieldDesc(name = "税收分类编码")
  private String taxCategoryCode;

  @FieldDesc(name = "税收分类")
  private String taxCategory;

  @FieldDesc(name = "发票显示名称")
  private String displayName;

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
