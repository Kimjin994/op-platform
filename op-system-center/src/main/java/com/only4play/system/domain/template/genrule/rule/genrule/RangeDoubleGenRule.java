package com.only4play.system.domain.template.genrule.rule.genrule;

import com.apifan.common.random.source.NumberSource;
import com.only4play.system.domain.template.templateitem.ValueType;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

public class RangeDoubleGenRule extends AbstractGenRule{

    @Setter
    @Getter
    private Double min;

    @Setter
    @Getter
    private Double max;

    @Setter
    @Getter
    private Integer listSize = 1;

    public RangeDoubleGenRule(Double min, Double max, Integer listSize) {
        super(GenTypeEnum.NUMBER_RANGE);
        this.min = min;
        this.max = max;
        this.listSize = listSize;
    }



    @Override
    public boolean support(ValueType valueType) {
        return Objects.equals(ValueType.NUMBER,valueType);
    }

    @Override
    public Object process(String property, Map<String, Object> lastMap) {
        double number = NumberSource.getInstance().randomDouble(this.min, this.max);
        return number;
    }

    @Override
    public Integer getListSize() {
        return this.listSize;
    }
}
