package com.only4play.system.domain.template.genrule.rule.genrule;

import com.apifan.common.random.source.AreaSource;
import com.only4play.system.domain.template.templateitem.ValueType;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

public class MapPointGenRule extends AbstractGenRule{

    @Setter
    @Getter
    private Integer listSize = 1;

    public MapPointGenRule(Integer listSize) {
        super(GenTypeEnum.MAP_POINT);
        this.listSize = listSize;
    }

    @Override
    public boolean support(ValueType valueType) {
        return Objects.equals(ValueType.STRING,valueType);
    }

    @Override
    public Object process(String property, Map<String, Object> lastMap) {
        //随机生成1个纬度
        double lat = AreaSource.getInstance().randomLatitude();
        //随机生成1个经度
        double lng = AreaSource.getInstance().randomLongitude();
        return lat + "," + lng;
    }

    @Override
    public Integer getListSize() {
        return this.listSize;
    }
}
