package com.only4play.system.domain.trade.orderlifecycle;

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
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.only4play.system.domain.trade.orderlifecycle.vo")
@GenCreator(pkgName = "com.only4play.system.domain.trade.orderlifecycle.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.trade.orderlifecycle.updater")
@GenQuery(pkgName = "com.only4play.system.domain.trade.orderlifecycle.query")
@Entity
@Table(name = "order_lifecycle")
@Data
public class OrderLifecycle extends BaseJpaAggregate {

  private Long flowNo;

  @FieldDesc(name = "操作类型")
  @Convert(converter = OrderOperateTypeConverter.class)
  @TypeConverter(toTypeFullName = "java.lang.Integer")
  private OrderOperateType operateType;

  private String remark;

  private String operateUser;

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
