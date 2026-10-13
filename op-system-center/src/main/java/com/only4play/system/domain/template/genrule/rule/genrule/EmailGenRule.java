package com.only4play.system.domain.template.genrule.rule.genrule;

import com.apifan.common.random.source.InternetSource;
import com.only4play.system.domain.template.templateitem.ValueType;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

public class EmailGenRule extends AbstractGenRule {

    @Setter
    @Getter
    private final Integer maxLength;

    @Setter
    @Getter
    private final String suffix;

    @Setter
    @Getter
    private Integer listSize = 1;

    public EmailGenRule(Integer maxLength, String suffix,Integer listSize) {
        super(GenTypeEnum.EMAIL);
        this.maxLength = maxLength;
        this.suffix = suffix;
        this.listSize = listSize;
    }


    @Override
    public boolean support(ValueType valueType) {
        return Objects.equals(ValueType.STRING, valueType);
    }

    @Override
    public Object process(String property, Map<String, Object> lastMap) {
        return InternetSource.getInstance().randomEmail(this.maxLength, this.suffix);
    }

    @Override
    public Integer getListSize() {
        return this.listSize;
    }
}
