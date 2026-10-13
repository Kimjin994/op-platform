package com.only4play.system.domain.template.mockrule.domainservice.plugin;

import com.apifan.common.random.source.NumberSource;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockConfig;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockContext;
import com.only4play.system.infrastructure.model.CodeValue;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class RangeLongMockPlugin implements IMockPlugin{

    public static final String RANGE_LONG_MOCK_CODE = "rangeLong";

    public static final String RANGE_LONG_MOCK_NAME = "范围整数";

    public static final String RANGE_LONG_PARAM_MIN_NAME = "最小值";

    public static final String RANGE_LONG_PARAM_MIN = "min";

    public static final String RANGE_LONG_PARAM_MAX_NAME = "最大值";

    public static final String RANGE_LONG_PARAM_MAX = "max";

    @Override
    public Map<String, Object> getGenData(String code, Map<String, Object> params) {
        Long min = MapUtils.getLong(params,RANGE_LONG_PARAM_MIN,0L);
        Long max = MapUtils.getLong(params,RANGE_LONG_PARAM_MAX,100000L);
        return listBasedGenerate(code,params,() -> NumberSource.getInstance().randomLong(min, max));
    }

    @Override
    public MockConfig getConfigInfo() {
        MockConfig config = new MockConfig();
        config.setCode(RANGE_LONG_MOCK_CODE);
        config.setName(RANGE_LONG_MOCK_NAME);
        List<CodeValue> params = getDefaultParamsList();
        CodeValue p1 = new CodeValue();
        p1.setK(RANGE_LONG_PARAM_MIN);
        p1.setV(RANGE_LONG_PARAM_MIN_NAME);
        params.add(p1);
        CodeValue p2 = new CodeValue();
        p2.setK(RANGE_LONG_PARAM_MAX);
        p2.setV(RANGE_LONG_PARAM_MAX_NAME);
        params.add(p2);
        config.setParams(params);
        return config;
    }

    @Override
    public boolean supports(MockContext delimiter) {
        return Objects.equals(delimiter.getCode(),RANGE_LONG_MOCK_CODE);
    }
}
