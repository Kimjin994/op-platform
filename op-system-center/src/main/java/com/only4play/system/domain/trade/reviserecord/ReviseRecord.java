package com.only4play.system.domain.trade.reviserecord;

import com.only4play.codegen.processor.creator.GenCreator;
import com.only4play.codegen.processor.creator.IgnoreCreator;
import com.only4play.codegen.processor.query.GenQuery;
import com.only4play.codegen.processor.query.QueryItem;
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

@GenVo(pkgName = "com.only4play.system.domain.trade.reviserecord.vo")
@GenCreator(pkgName = "com.only4play.system.domain.trade.reviserecord.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.trade.reviserecord.updater")
@GenQuery(pkgName = "com.only4play.system.domain.trade.reviserecord.query")
@Entity
@Table(name = "revise_record")
@Data
public class ReviseRecord extends BaseJpaAggregate {

  @FieldDesc(name = "操作人")
  private String operateUser;

  @QueryItem
  @FieldDesc(name = "唯一流水")
  private Long flowNo;

  @FieldDesc(name = "修订前")
  private String reviseBefore;

  @FieldDesc(name = "修订后")
  private String reviseAfter;

  @FieldDesc(name = "差别")
  private String diff;

  @FieldDesc(name = "表名")
  @QueryItem
  private String tableName;

  @FieldDesc(name = "修订原因")
  private String reviseReason;

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
