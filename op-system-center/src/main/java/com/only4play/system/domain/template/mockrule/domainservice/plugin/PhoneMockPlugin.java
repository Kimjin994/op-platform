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
public class PhoneMockPlugin implements IMockPlugin{

    public static final String PHONE_MOCK_CODE = "phone";

    public static final String PHONE_MOCK_NAME = "手机号";

    @Override
    public Map<String, Object> getGenData(String code, Map<String, Object> params) {
        return listBasedGenerate(code,params,() -> PersonInfoSource.getInstance().randomChineseMobile());
    }

    @Override
    public MockConfig getConfigInfo() {
        MockConfig config = new MockConfig();
        config.setCode(PHONE_MOCK_CODE);
        config.setName(PHONE_MOCK_NAME);
        List<CodeValue> params = getDefaultParamsList();
        config.setParams(params);
        return config;
    }

    @Override
    public boolean supports(MockContext delimiter) {
        return Objects.equals(delimiter.getCode(),PHONE_MOCK_CODE);
    }
}
