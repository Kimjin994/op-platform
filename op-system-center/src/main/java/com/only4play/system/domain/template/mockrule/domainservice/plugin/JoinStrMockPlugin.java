package com.only4play.system.domain.template.mockrule.domainservice.plugin;

import com.google.common.collect.Lists;
import com.only4play.common.exception.BusinessException;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockConfig;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockContext;
import com.only4play.system.infrastructure.constants.TemplateErrorCode;
import com.only4play.system.infrastructure.model.CodeValue;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JoinStrMockPlugin implements IMockPlugin {

    public static final String JOIN_MOCK_CODE = "join";

    public static final String JOIN_MOCK_NAME = "字符串拼接";

    public static final String JOIN_PARAM_PREFIX_NAME = "前缀";

    public static final String JOIN_PARAM_PREFIX = "prefix";

    public static final String JOIN_PARAM_SUFFIX_NAME = "后缀";

    public static final String JOIN_PARAM_SUFFIX = "suffix";

    @Override
    public Map<String, Object> getGenData(String code, Map<String, Object> params) {
        String prefix = MapUtils.getString(params, JOIN_PARAM_PREFIX, "");
        String suffix = MapUtils.getString(params, JOIN_PARAM_SUFFIX, "");
        Object lastData = MapUtils.getObject(params, LAST_DATA, null);
        if(Objects.isNull(lastData)){
            throw new BusinessException(TemplateErrorCode.JOIN_STR_CONFIG_WRONG);
        }
        Map<String, Object> result = new HashMap<>();
        //如果是一个集合
        if (lastData instanceof Collection) {
            int size = CollectionUtils.size(lastData);
            List<Object> data = Lists.newArrayList();
            for (int i = 0; i < size; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(prefix);
                sb.append(CollectionUtils.get(lastData, i).toString());
                sb.append(suffix);
                data.add(sb.toString());
            }
            result.put(code, data);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(prefix).append(lastData.toString()).append(suffix);
            result.put(code, sb.toString());
        }
        return result;
    }

    @Override
    public MockConfig getConfigInfo() {
        MockConfig config = new MockConfig();
        config.setCode(JOIN_MOCK_CODE);
        config.setName(JOIN_MOCK_NAME);
        List<CodeValue> params = getDefaultParamsList();
        CodeValue p1 = new CodeValue();
        p1.setK(JOIN_PARAM_PREFIX);
        p1.setV(JOIN_PARAM_PREFIX_NAME);
        params.add(p1);
        CodeValue p2 = new CodeValue();
        p2.setK(JOIN_PARAM_SUFFIX);
        p2.setV(JOIN_PARAM_SUFFIX_NAME);
        params.add(p2);
        config.setParams(params);
        return config;
    }

    @Override
    public boolean supports(MockContext delimiter) {
        return Objects.equals(delimiter.getCode(), JOIN_MOCK_CODE);
    }
}
