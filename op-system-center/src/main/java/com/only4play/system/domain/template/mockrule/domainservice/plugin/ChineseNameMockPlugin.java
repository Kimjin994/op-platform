package com.only4play.system.domain.template.mockrule.domainservice.plugin;

import com.apifan.common.random.source.PersonInfoSource;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockConfig;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockContext;
import com.only4play.system.infrastructure.model.CodeValue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ChineseNameMockPlugin implements IMockPlugin{

    public static final String CHINESE_NAME_MOCK_CODE = "chineseName";

    public static final String CHINESE_NAME_MOCK_NAME = "中文名";

    @Override
    public Map<String, Object> getGenData(String code, Map<String, Object> params) {
        return listBasedGenerate(code,params,() -> PersonInfoSource.getInstance().randomChineseName());
    }

    @Override
    public MockConfig getConfigInfo() {
        MockConfig config = new MockConfig();
        config.setCode(CHINESE_NAME_MOCK_CODE);
        config.setName(CHINESE_NAME_MOCK_NAME);
        List<CodeValue> params = getDefaultParamsList();
        config.setParams(params);
        return config;
    }

    @Override
    public boolean supports(MockContext delimiter) {
        return Objects.equals(delimiter.getCode(),CHINESE_NAME_MOCK_CODE);
    }
}
