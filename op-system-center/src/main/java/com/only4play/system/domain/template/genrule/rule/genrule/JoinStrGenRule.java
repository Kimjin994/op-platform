package com.only4play.system.domain.template.genrule.rule.genrule;

import com.only4play.system.domain.template.templateitem.ValueType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.MapUtils;

import java.util.Map;
import java.util.Objects;

public class JoinStrGenRule extends AbstractGenRule {

    @Setter
    @Getter
    private final String prefix;

    @Setter
    @Getter
    private final String suffix;

    @Setter
    @Getter
    private Integer listSize = 1;

    public JoinStrGenRule(String prefix, String suffix,Integer listSize) {
        super(GenTypeEnum.JOIN_STR);
        this.prefix = prefix;
        this.suffix = suffix;
        this.listSize = listSize;
    }

    @Override
    public boolean support(ValueType valueType) {
        return Objects.equals(ValueType.STRING, valueType);
    }

    @Override
    public Object process(String property, Map<String, Object> lastMap) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix)
                .append(MapUtils.getString(lastMap, property))
                .append(this.suffix);
        return sb.toString();
    }

    @Override
    public Integer getListSize() {
        return this.listSize;
    }
}
