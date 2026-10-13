package com.only4play.system.domain.asset.assetrecord;

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

@GenVo(pkgName = "com.only4play.system.domain.asset.assetrecord.vo")
@GenCreator(pkgName = "com.only4play.system.domain.asset.assetrecord.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.asset.assetrecord.updater")
@GenQuery(pkgName = "com.only4play.system.domain.asset.assetrecord.query")
@Entity
@Table(name = "asset_in_out_record")
@Data
public class AssetInOutRecord extends BaseJpaAggregate {

  @FieldDesc(name = "手动录入批次号,仅用于展示")
  @QueryItem
  private String batchNo;

  @FieldDesc(name = "自动生成的批次号，防止重复")
  private String genBatchNo;

  @FieldDesc(name = "出入库业务类型")
  @Convert(converter = InOutBizTypeConverter.class)
  private InOutBizType inOutBizType;

  @FieldDesc(name = "操作人")
  private String operateUser;

  @FieldDesc(name = "出入类型")
  @Convert(converter = InOutTypeConverter.class)
  private InOutType inOutType;

  @FieldDesc(name = "仓库Id")
  private Long wareHouseId;

  @FieldDesc(name = "总数")
  private Integer totalCount;

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
