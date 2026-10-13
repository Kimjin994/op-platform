package com.only4play.system.domain.template.mockrule.domainservice.plugin;

import com.apifan.common.random.source.OtherSource;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockConfig;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockContext;
import com.only4play.system.infrastructure.model.CodeValue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CarNoMockPlugin implements IMockPlugin{

    public static final String CAR_NO_MOCK_CODE = "carNo";

    public static final String CAR_NO_MOCK_NAME = "车牌号";

    @Override
    public Map<String, Object> getGenData(String code, Map<String, Object> params) {
        return listBasedGenerate(code,params,() -> OtherSource.getInstance().randomPlateNumber());
    }

    @Override
    public MockConfig getConfigInfo() {
        MockConfig config = new MockConfig();
        config.setCode(CAR_NO_MOCK_CODE);
        config.setName(CAR_NO_MOCK_NAME);
        List<CodeValue> params = getDefaultParamsList();
        config.setParams(params);
        return config;
    }

    @Override
    public boolean supports(MockContext delimiter) {
        return Objects.equals(delimiter.getCode(),CAR_NO_MOCK_CODE);
    }
}
