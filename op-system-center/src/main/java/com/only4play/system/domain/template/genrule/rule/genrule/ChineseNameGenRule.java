package com.only4play.system.domain.template.genrule.rule.genrule;

import com.apifan.common.random.source.PersonInfoSource;
import com.only4play.system.domain.template.templateitem.ValueType;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

public class ChineseNameGenRule extends AbstractGenRule{

    @Setter
    @Getter
    private Integer listSize = 1;

    public ChineseNameGenRule(Integer listSize) {
        super(GenTypeEnum.CHINESE_NAME);
        this.listSize = listSize;
    }

    @Override
    public boolean support(ValueType valueType) {
        return Objects.equals(ValueType.STRING,valueType);
    }

    @Override
    public Object process(String property, Map<String, Object> lastMap) {
        return PersonInfoSource.getInstance().randomChineseName();
    }

    @Override
    public Integer getListSize() {
        return this.listSize;
    }
}
