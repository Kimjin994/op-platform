package com.only4play.system.domain.template.mockrule.domainservice.plugin;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockConfig;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockContext;
import com.only4play.system.infrastructure.model.CodeValue;
import org.apache.commons.collections.MapUtils;
import org.springframework.plugin.core.Plugin;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public interface IMockPlugin extends Plugin<MockContext> {

    /**
     * list 参数
     */
    String LIST_PARAM = "listSize";

    Integer DEFAULT_LIST_SIZE = 1;

    String LAST_DATA = "lastData";

    /**
     * 根据规则编码生成
     *
     * @param code
     * @param params
     * @return
     */
    Map<String, Object> getGenData(String code, Map<String, Object> params);

    MockConfig getConfigInfo();

    /**
     * 默认参数
     *
     * @return
     */
    default List<CodeValue> getDefaultParamsList() {
        List<CodeValue> defaultParams = Lists.newArrayList();
        CodeValue codeValue = new CodeValue();
        codeValue.setK(LIST_PARAM);
        codeValue.setV("数量");
        defaultParams.add(codeValue);
        return defaultParams;
    }


    default Map<String, Object> listBasedGenerate(String code, Map<String, Object> params, Supplier<Object> supplier) {
        Map<String, Object> result = Maps.newHashMap();
        Integer listSize = getListSize(params);
        if (listSize > DEFAULT_LIST_SIZE) {
            List<Object> dataList = Lists.newArrayList();
            for (int i = 0; i < listSize; i++) {
                dataList.add(supplier.get());
            }
            result.put(code, dataList);
        } else {
            result.put(code, supplier.get());
        }
        return result;
    }

    /**
     * 获取配置的List 大小
     *
     * @param params
     * @return
     */
    default Integer getListSize(Map<String, Object> params) {
        return MapUtils.getInteger(params, LIST_PARAM, DEFAULT_LIST_SIZE);
    }


}
