package com.only4play.system.domain.template.mockrule.domainservice.plugin;

import com.only4play.common.constants.ValidStatus;
import com.only4play.system.domain.template.mockrule.domainservice.IMockRuleDomainService;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockConfig;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockContext;
import com.only4play.system.domain.template.objecttemplate.service.IObjectTemplateService;
import com.only4play.system.infrastructure.model.CodeValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class NestedObjectMockPlugin implements IMockPlugin {

    private final IObjectTemplateService objectTemplateService;
    private final IMockRuleDomainService mockRuleDomainService;

    public static final String NESTED_OBJECT_MOCK_CODE = "nestedObject";

    public static final String NESTED_OBJECT_MOCK_NAME = "嵌套对象";

    public static final String NESTED_OBJECT_PARAM_TEMPLATE_CODE = "templateCode";

    public static final String NESTED_OBJECT_PARAM_TEMPLATE_CODE_NAME = "模板编码";


    @Override
    public Map<String, Object> getGenData(String code, Map<String, Object> params) {
        String templateCode = MapUtils.getString(params,NESTED_OBJECT_PARAM_TEMPLATE_CODE,"");
        return listBasedGenerate(code, params, () -> mockRuleDomainService.getMockDateByTemplateCode(templateCode));
    }

    @Override
    public MockConfig getConfigInfo() {
        MockConfig config = new MockConfig();
        config.setCode(NESTED_OBJECT_MOCK_CODE);
        config.setName(NESTED_OBJECT_MOCK_NAME);
        List<CodeValue> params = getDefaultParamsList();
        CodeValue p1 = new CodeValue();
        p1.setK(NESTED_OBJECT_PARAM_TEMPLATE_CODE);
        p1.setV(NESTED_OBJECT_PARAM_TEMPLATE_CODE_NAME);
        params.add(p1);
        config.setParams(params);
        Map<String, Object> ext = new HashMap<>();
        List<String> allCodes = objectTemplateService.findAll()
                .stream()
                .filter(o -> Objects.equals(o.getValidStatus(), ValidStatus.VALID))
                .map(v -> v.getCode())
                .collect(Collectors.toList());
        ext.put("codes", allCodes);
        config.setExt(ext);
        return config;
    }

    @Override
    public boolean supports(MockContext delimiter) {
        return StringUtils.equals(delimiter.getCode(), NESTED_OBJECT_MOCK_CODE);
    }
}
