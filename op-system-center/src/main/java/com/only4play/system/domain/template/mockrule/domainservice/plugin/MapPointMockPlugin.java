package com.only4play.system.domain.template.mockrule.domainservice.plugin;

import com.apifan.common.random.source.AreaSource;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockConfig;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockContext;
import com.only4play.system.infrastructure.model.CodeValue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MapPointMockPlugin implements IMockPlugin {

    public static final String LOCATION_MOCK_CODE = "location";

    public static final String LOCATION_MOCK_NAME = "位置";

    @Override
    public Map<String, Object> getGenData(String code, Map<String, Object> params) {
        //随机生成1个纬度
        double lat = AreaSource.getInstance().randomLatitude();
        //随机生成1个经度
        double lng = AreaSource.getInstance().randomLongitude();
        String result = lat + "," + lng;
        return listBasedGenerate(code, params, () -> result);
    }

    @Override
    public MockConfig getConfigInfo() {
        MockConfig config = new MockConfig();
        config.setCode(LOCATION_MOCK_CODE);
        config.setName(LOCATION_MOCK_NAME);
        List<CodeValue> params = getDefaultParamsList();
        config.setParams(params);
        return config;
    }

    @Override
    public boolean supports(MockContext delimiter) {
        return Objects.equals(delimiter.getCode(), LOCATION_MOCK_CODE);
    }
}
