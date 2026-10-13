package com.only4play.system.template.mock;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.only4play.system.domain.template.mockrule.creator.MockRuleConfigCreator;
import com.only4play.system.domain.template.mockrule.domainservice.plugin.EmailMockPlugin;
import com.only4play.system.domain.template.mockrule.service.IMockRuleConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MockPluginTest {

    @Autowired
    private IMockRuleConfigService mockRuleConfigService;

    /**
     * 添加生成规则
     */
    @Test
    public void testAddMockRule(){
        MockRuleConfigCreator creator  = new MockRuleConfigCreator();
        creator.setCode("email");
        creator.setName("邮件生成");
        Map<String,Object> params = Maps.newHashMap();
        params.put(EmailMockPlugin.EMAIL_PARAM_MAXLENGTH,8);
        params.put(EmailMockPlugin.EMAIL_PARAM_SUFFIX,"qq.com");
        creator.setParams(JSON.toJSONString(params));
        mockRuleConfigService.createMockRuleConfig(creator);
    }

    /**
     * 添加mock规则的关系
     */
    @Test
    public void testAddMockRel(){

    }

    /**
     * 测试生成逻辑
     */
    @Test
    public void testMock(){

    }
}
