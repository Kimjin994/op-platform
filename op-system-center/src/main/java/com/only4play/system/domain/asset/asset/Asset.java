package com.only4play.system.domain.asset.asset;

import com.only4play.codegen.processor.creator.GenCreator;
import com.only4play.codegen.processor.creator.IgnoreCreator;
import com.only4play.codegen.processor.query.GenQuery;
import com.only4play.codegen.processor.updater.GenUpdater;
import com.only4play.codegen.processor.updater.IgnoreUpdater;
import com.only4play.codegen.processor.vo.GenVo;
import com.only4play.common.annotation.FieldDesc;
import com.only4play.common.constants.ValidStatus;
import com.only4play.common.exception.BusinessException;
import com.only4play.jpa.converter.ValidStatusConverter;
import com.only4play.jpa.support.BaseJpaAggregate;
import com.only4play.system.domain.asset.asset.domainservice.model.AssetBizInfo;
import com.only4play.system.domain.asset.asset.events.AssetEvents;
import com.only4play.system.infrastructure.constants.AssetErrorCode;
import java.util.Objects;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.only4play.system.domain.asset.asset.vo")
@GenCreator(pkgName = "com.only4play.system.domain.asset.asset.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.asset.asset.updater")
@GenQuery(pkgName = "com.only4play.system.domain.asset.asset.query")
@Entity
@Table(name = "asset")
@Data
public class Asset extends BaseJpaAggregate {

    @FieldDesc(name = "仓库id")
    private Long houseId;

    @Convert(converter = ValidStatusConverter.class)
    @IgnoreUpdater
    @IgnoreCreator
    private ValidStatus validStatus;

    @FieldDesc(name = "资产名称")
    private String name;

    @FieldDesc(name = "唯一编码")
    private String uniqueCode;

    @FieldDesc(name = "skuId")
    private Long skuId;


    public void init(){
        setValidStatus(ValidStatus.VALID);
    }


    /**
     * 入库
     */
    public void in(AssetBizInfo bizInfo){
        if(Objects.equals(ValidStatus.VALID,this.getValidStatus())){
            throw new BusinessException(AssetErrorCode.ASSET_HAS_IN);
        }
        setValidStatus(ValidStatus.VALID);
        registerEvent(new AssetEvents.AssetInEvent(this, bizInfo));
    }

    /**
     * 出库
     */
    public void out(AssetBizInfo bizInfo){
        if(Objects.equals(ValidStatus.INVALID,this.getValidStatus())){
            throw new BusinessException(AssetErrorCode.ASSET_HAS_OUT);
        }
        setValidStatus(ValidStatus.INVALID);
        registerEvent(new AssetEvents.AssetOutEvent(this, bizInfo));
    }

}
