package com.only4play.system.domain.template.mockrule.domainservice.plugin;

import com.apifan.common.random.source.DateTimeSource;
import com.google.common.collect.Lists;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockConfig;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockContext;
import com.only4play.system.infrastructure.model.CodeValue;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class DateMockPlugin implements IMockPlugin{

    public static final String DATE_MOCK_CODE = "date";

    public static final String DATE_MOCK_NAME = "日期";

    public static final String DATE_PARAM_PATTERN_NAME = "格式";

    public static final String DATE_PARAM_PATTERN = "pattern";

    public static final String DATE_PARAM_YEAR_NAME = "年份";

    public static final String DATE_PARAM_YEAR = "year";

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    public static final String DEFAULT_DATE_PATTERN2 = "yyyy-MM-dd HH:mm:ss";


    @Override
    public Map<String, Object> getGenData(String code, Map<String, Object> params) {
        Integer year = MapUtils.getInteger(params,DATE_PARAM_YEAR, LocalDate.now().getYear());
        String pattern = MapUtils.getString(params,DATE_PARAM_PATTERN,DEFAULT_DATE_PATTERN);
        return listBasedGenerate(code,params,() -> DateTimeSource.getInstance().randomDate(year,pattern));
    }

    @Override
    public MockConfig getConfigInfo() {
        MockConfig config = new MockConfig();
        config.setCode(DATE_MOCK_CODE);
        config.setName(DATE_MOCK_NAME);
        List<CodeValue> params = getDefaultParamsList();
        CodeValue p1 = new CodeValue();
        p1.setK(DATE_PARAM_PATTERN);
        p1.setV(DATE_PARAM_PATTERN_NAME);
        params.add(p1);
        CodeValue p2 = new CodeValue();
        p2.setK(DATE_PARAM_YEAR);
        p2.setV(DATE_PARAM_YEAR_NAME);
        params.add(p2);
        config.setParams(params);
        Map<String,Object> ext = new HashMap<>();
        List<String> patterns = Lists.newArrayList();
        patterns.add(DEFAULT_DATE_PATTERN);
        patterns.add(DEFAULT_DATE_PATTERN2);
        ext.put("patterns", patterns);
        config.setExt(ext);
        return config;
    }

    @Override
    public boolean supports(MockContext delimiter) {
        return Objects.equals(delimiter.getCode(),DATE_MOCK_CODE);
    }
}
