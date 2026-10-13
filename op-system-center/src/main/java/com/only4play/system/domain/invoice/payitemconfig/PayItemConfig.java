package com.only4play.system.domain.invoice.payitemconfig;

import com.only4play.codegen.processor.creator.GenCreator;
import com.only4play.codegen.processor.query.GenQuery;
import com.only4play.codegen.processor.updater.GenUpdater;
import com.only4play.codegen.processor.vo.GenVo;
import com.only4play.common.annotation.FieldDesc;
import com.only4play.common.annotation.TypeConverter;
import com.only4play.common.constants.ValidStatus;
import com.only4play.jpa.converter.ValidStatusConverter;
import com.only4play.jpa.support.BaseJpaAggregate;
import com.only4play.order.commons.pay.PayType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

/**
 * 支付方式开票配置
 */
@GenVo(pkgName = "com.only4play.system.domain.invoice.payitemconfig.vo")
@GenCreator(pkgName = "com.only4play.system.domain.invoice.payitemconfig.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.invoice.payitemconfig.updater")
@GenQuery(pkgName = "com.only4play.system.domain.invoice.payitemconfig.query")
@Entity
@Table(name = "pay_item_config")
@Data
public class PayItemConfig extends BaseJpaAggregate {

  @FieldDesc(name = "交易类型编码")
  private String tradeTypeCode;

  @FieldDesc(name = "支付类型")
  @Convert(converter = PayTypeConverter.class)
  @TypeConverter(toTypeFullName = "java.lang.Integer")
  private PayType payType;

  @FieldDesc(name = "是否支持开票")
  @Convert(converter = ValidStatusConverter.class)
  @TypeConverter(toTypeFullName = "java.lang.Integer")
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
