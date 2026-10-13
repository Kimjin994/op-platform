package com.only4play.system.template.mock;

import com.google.common.collect.Lists;
import com.only4play.common.constants.ValidStatus;
import com.only4play.system.domain.template.verifyrule.VerifyRuleConfig;
import com.only4play.system.domain.template.verifyrule.creator.VerifyRuleConfigCreator;
import com.only4play.system.domain.template.verifyrule.mapper.VerifyRuleConfigMapper;
import com.only4play.system.domain.template.verifyrule.repository.VerifyRuleConfigRepository;
import com.only4play.system.domain.template.verifyrule.rule.MaxVerifyRule;
import com.only4play.system.domain.template.verifyrule.rule.VerifyRule;
import com.only4play.system.domain.template.verifyrule.service.IVerifyRuleConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class VerifyRuleTest {

    @Autowired
    private IVerifyRuleConfigService verifyRuleConfigService;

    @Autowired
    private VerifyRuleConfigRepository verifyRuleConfigRepository;



    @Test
    public void testAddVerifyRule(){
        VerifyRuleConfigCreator configCreator = new VerifyRuleConfigCreator();
        configCreator.setName("规则");
        configCreator.setDescInfo("info");
        configCreator.setItemId(1L);
        List<VerifyRule> ruleList = Lists.newArrayList();
        MaxVerifyRule maxVerifyRule = new MaxVerifyRule();
        maxVerifyRule.setMaxValue(new BigDecimal(3));
        ruleList.add(maxVerifyRule);
        configCreator.setRuleList(ruleList);
        verifyRuleConfigService.createVerifyRuleConfig(configCreator);
    }

    @Test
    public void testSave(){
        VerifyRuleConfigCreator configCreator = new VerifyRuleConfigCreator();
        configCreator.setName("规则");
        configCreator.setDescInfo("info");
        configCreator.setItemId(1L);
        List<VerifyRule> ruleList = Lists.newArrayList();
        MaxVerifyRule maxVerifyRule = new MaxVerifyRule();
        maxVerifyRule.setMaxValue(new BigDecimal(3));
        ruleList.add(maxVerifyRule);
        configCreator.setRuleList(ruleList);
        VerifyRuleConfig ruleConfig = VerifyRuleConfigMapper.INSTANCE.dtoToEntity(configCreator);
        ruleConfig.setValidStatus(ValidStatus.VALID);
        verifyRuleConfigRepository.save(ruleConfig);
    }
}
