package com.only4play.system.template.mock;

import com.alibaba.fastjson.JSON;
import com.only4play.system.domain.template.genrule.domainservice.IGenRuleDomainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class MockServiceTest {

    @Autowired
    private IGenRuleDomainService genRuleDomainService;


    @Test
    public void testMockUser() {
        Map<String, Object> result = genRuleDomainService.genJsonObjectMapByTemplateId(6L);
        System.out.println(JSON.toJSONString(result));
    }
}
