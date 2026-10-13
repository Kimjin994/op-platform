package com.only4play.system.domain.template.mockrule.domainservice.plugin;

import com.apifan.common.random.source.InternetSource;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockConfig;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockContext;
import com.only4play.system.infrastructure.model.CodeValue;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class EmailMockPlugin implements IMockPlugin{

    public static final String EMAIL_MOCK_CODE = "email";

    public static final String EMAIL_MOCK_NAME = "邮件";

    public static final String EMAIL_PARAM_MAXLENGTH_NAME = "最大长度";

    public static final String EMAIL_PARAM_MAXLENGTH = "maxLength";

    public static final String EMAIL_PARAM_SUFFIX_NAME = "后缀";

    public static final String EMAIL_PARAM_SUFFIX = "suffix";

    @Override
    public Map<String, Object> getGenData(String code, Map<String, Object> params) {
        Integer maxLength = MapUtils.getInteger(params,EMAIL_PARAM_MAXLENGTH,8);
        String suffix = MapUtils.getString(params,EMAIL_PARAM_SUFFIX,"com");
        return listBasedGenerate(code,params,() -> InternetSource.getInstance().randomEmail(maxLength, suffix));
    }

    @Override
    public MockConfig getConfigInfo() {
        MockConfig config = new MockConfig();
        config.setCode(EMAIL_MOCK_CODE);
        config.setName(EMAIL_MOCK_NAME);
        List<CodeValue> params = getDefaultParamsList();
        CodeValue p1 = new CodeValue();
        p1.setK(EMAIL_PARAM_MAXLENGTH);
        p1.setV(EMAIL_PARAM_MAXLENGTH_NAME);
        params.add(p1);
        CodeValue p2 = new CodeValue();
        p2.setK(EMAIL_PARAM_SUFFIX);
        p2.setV(EMAIL_PARAM_SUFFIX_NAME);
        params.add(p2);
        config.setParams(params);
        return config;
    }


    @Override
    public boolean supports(MockContext delimiter) {
        return Objects.equals(delimiter.getCode(),EMAIL_MOCK_CODE);
    }
}
