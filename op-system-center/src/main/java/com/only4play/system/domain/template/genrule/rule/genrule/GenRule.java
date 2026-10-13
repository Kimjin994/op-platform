package com.only4play.system.domain.template.genrule.rule.genrule;

import com.only4play.system.domain.template.templateitem.ValueType;

import java.util.Map;

public interface GenRule{
    /**
     * 根据属性获取mock键值对
     * @param property
     * @return oldMap 之前获取的键值对，方便多genRule 下进行连续处理
     */
    Map<String,Object> getGenData(String property, Map<String,Object> lastMap, ValueType valueType);

    GenTypeEnum genType();

    Integer getListSize();

}
