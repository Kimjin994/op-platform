package com.only4play.system.domain.template.genrule.rule.genrule;

import com.apifan.common.random.source.DateTimeSource;
import com.only4play.system.domain.template.templateitem.ValueType;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

public class DateGenRule extends AbstractGenRule{

    @Setter
    @Getter
    private final String pattern;

    @Setter
    @Getter
    private final Integer year;

    @Setter
    @Getter
    private Integer listSize = 1;

    protected DateGenRule(String pattern, Integer year,Integer listSize) {
        super(GenTypeEnum.DATE);
        this.pattern = pattern;
        this.year = year;
        this.listSize = listSize;
    }

    @Override
    public boolean support(ValueType valueType) {
        return Objects.equals(ValueType.STRING,valueType);
    }

    @Override
    public Object process(String property, Map<String, Object> lastMap) {
        return DateTimeSource.getInstance().randomDate(year,pattern);
    }
}
