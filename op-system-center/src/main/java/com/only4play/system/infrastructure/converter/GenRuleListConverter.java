package com.only4play.system.infrastructure.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.only4play.system.domain.template.genrule.rule.genrule.GenRule;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.AttributeConverter;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class GenRuleListConverter implements AttributeConverter<List<GenRule>,String> {

    final static Type type = new TypeReference<List<GenRule>>() {}.getType();

    @Override
    public String convertToDatabaseColumn(List<GenRule> feeRules) {
        return JSON.toJSONString(feeRules, SerializerFeature.WriteClassName);
    }

    @Override
    public List<GenRule> convertToEntityAttribute(String s) {
        if(StringUtils.isEmpty(s)){
            return Collections.emptyList();
        }else{
            return JSON.parseObject(s,type);
        }
    }
}
