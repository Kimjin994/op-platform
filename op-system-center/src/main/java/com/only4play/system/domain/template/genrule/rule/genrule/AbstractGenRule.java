package com.only4play.system.domain.template.genrule.rule.genrule;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.only4play.system.domain.template.templateitem.ValueType;

import java.util.List;
import java.util.Map;

public abstract class AbstractGenRule implements GenRule{

    private final GenTypeEnum genTypeEnum;

    protected AbstractGenRule(GenTypeEnum genTypeEnum) {
        this.genTypeEnum = genTypeEnum;
    }


    @Override
    public Map<String, Object> getGenData(String property, Map<String, Object> lastMap, ValueType valueType) {
        if(support(valueType)){
            Map<String,Object> map = Maps.newHashMap();
            if(getListSize() > 1){
                List<Object> objectList = Lists.newArrayList();
                for(int i=0;i<getListSize();i++){
                    Object obj = process(property, lastMap);
                    objectList.add(obj);
                }
                map.put(property,objectList);
            }else {
                Object obj = process(property, lastMap);
                map.put(property,obj);
            }
            return map;
        }else {
            return lastMap;
        }
    }

    @Override
    public GenTypeEnum genType() {
        return this.genTypeEnum;
    }

    public abstract boolean support(ValueType valueType);

    public abstract Object process(String property, Map<String, Object> lastMap);

}
