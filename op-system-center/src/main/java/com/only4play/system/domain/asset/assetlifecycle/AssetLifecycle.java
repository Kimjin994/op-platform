package com.only4play.system.domain.asset.assetlifecycle;

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
import com.only4play.system.domain.asset.assetrecord.InOutBizType;
import com.only4play.system.domain.asset.assetrecord.InOutBizTypeConverter;
import com.only4play.system.domain.asset.assetrecord.InOutType;
import com.only4play.system.domain.asset.assetrecord.InOutTypeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.only4play.system.domain.asset.assetlifecycle.vo")
@GenCreator(pkgName = "com.only4play.system.domain.asset.assetlifecycle.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.asset.assetlifecycle.updater")
@GenQuery(pkgName = "com.only4play.system.domain.asset.assetlifecycle.query")
@Entity
@Table(name = "asset_lifecycle")
@Data
public class AssetLifecycle extends BaseJpaAggregate {

  @FieldDesc(name = "资产名称")
  private String name;

  @FieldDesc(name = "资产Id")
  private Long assetsId;

  private Long skuId;

  @FieldDesc(name = "唯一编码")
  @QueryItem
  private String uniqueCode;

  @FieldDesc(name = "备注")
  @Column(columnDefinition = "varchar(500)")
  private String remark;

  @FieldDesc(name = "仓库名称")
  private String houseName;

  @FieldDesc(name = "仓库id")
  private Long houseId;

  @FieldDesc(name = "出入库业务类型")
  @Convert(converter = InOutBizTypeConverter.class)
  private InOutBizType inOutBizType;

  @FieldDesc(name = "出入类型")
  @Convert(converter = InOutTypeConverter.class)
  private InOutType inOutType;

  @FieldDesc(name = "操作人")
  private String operateUser;

  @FieldDesc(name = "唯一批次号")
  private String genBatchNo;

  @FieldDesc(name = "批次号")
  private String batchNo;

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
