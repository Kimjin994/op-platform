package com.only4play.system.template.mock;


import com.google.common.collect.Lists;
import com.only4play.common.constants.ValidStatus;
import com.only4play.system.domain.template.genrule.GenRuleItemRel;
import com.only4play.system.domain.template.genrule.creator.GenRuleConfigCreator;
import com.only4play.system.domain.template.genrule.repository.GenRuleItemRelRepository;
import com.only4play.system.domain.template.genrule.rule.genrule.ChineseNameGenRule;
import com.only4play.system.domain.template.genrule.rule.genrule.EmailGenRule;
import com.only4play.system.domain.template.genrule.rule.genrule.GenRule;
import com.only4play.system.domain.template.genrule.rule.genrule.NestedObjectGenRule;
import com.only4play.system.domain.template.genrule.service.IGenRuleConfigService;
import com.only4play.system.domain.template.objecttemplate.TemplateItemRel;
import com.only4play.system.domain.template.objecttemplate.creator.ObjectTemplateCreator;
import com.only4play.system.domain.template.objecttemplate.repository.TemplateItemRelRepository;
import com.only4play.system.domain.template.objecttemplate.service.IObjectTemplateService;
import com.only4play.system.domain.template.templateitem.InputType;
import com.only4play.system.domain.template.templateitem.ValueType;
import com.only4play.system.domain.template.templateitem.creator.TemplateItemCreator;
import com.only4play.system.domain.template.templateitem.service.ITemplateItemService;
import com.only4play.system.domain.template.verifyrule.creator.VerifyRuleConfigCreator;
import com.only4play.system.domain.template.verifyrule.rule.MaxVerifyRule;
import com.only4play.system.domain.template.verifyrule.rule.VerifyRule;
import com.only4play.system.domain.template.verifyrule.service.IVerifyRuleConfigService;
import com.only4play.system.infrastructure.model.CodeValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TemplateServiceTest {

    @Autowired
    private IObjectTemplateService objectTemplateService;

    @Autowired
    private ITemplateItemService templateItemService;

    @Autowired
    private TemplateItemRelRepository templateItemRelRepository;

    @Autowired
    private IGenRuleConfigService genRuleConfigService;

    @Autowired
    private GenRuleItemRelRepository genRuleItemRelRepository;

    @Autowired
    private IVerifyRuleConfigService verifyRuleConfigService;

    /**
     * 添加模板
     */
    @Test
    public void addTemplate(){

        ObjectTemplateCreator objectTemplateCreator = new ObjectTemplateCreator();
        objectTemplateCreator.setCreateUser("张三");
        objectTemplateCreator.setCategoryCode("traffic");
        objectTemplateCreator.setCode("user");
        objectTemplateCreator.setName("用户");
        objectTemplateCreator.setDescription("描述用户");
        objectTemplateCreator.setCategoryId(1L);
        objectTemplateService.createObjectTemplate(objectTemplateCreator);

    }

    /**
     * 添加模板项
     */
    @Test
    public void addTemplateItem(){

        TemplateItemCreator itemCreator = new TemplateItemCreator();
        itemCreator.setCreateUser("李四");
        itemCreator.setCode("username");
        itemCreator.setInputType(InputType.TEXT);
        itemCreator.setRequireFlag(ValidStatus.VALID);
        itemCreator.setName("用户名");
        itemCreator.setValueType(ValueType.STRING);
        itemCreator.setExtList(Lists.newArrayList());
        templateItemService.createTemplateItem(itemCreator);

        TemplateItemCreator itemCreator1 = new TemplateItemCreator();
        itemCreator1.setCreateUser("李四");
        itemCreator1.setCode("email");
        itemCreator1.setInputType(InputType.TEXT);
        itemCreator1.setRequireFlag(ValidStatus.VALID);
        itemCreator1.setName("邮件");
        itemCreator1.setValueType(ValueType.STRING);
        itemCreator1.setExtList(Lists.newArrayList());
        templateItemService.createTemplateItem(itemCreator1);

    }

    @Test
    public void addNestedItem(){
        TemplateItemCreator itemCreator = new TemplateItemCreator();
        itemCreator.setCreateUser("李四");
        itemCreator.setCode("son");
        itemCreator.setInputType(InputType.TEXT);
        itemCreator.setRequireFlag(ValidStatus.VALID);
        itemCreator.setName("儿子");
        itemCreator.setValueType(ValueType.NESTED_OBJECT);
        List<CodeValue> rules = Lists.newArrayList();
        CodeValue cv = new CodeValue();
        cv.setK("2");
        cv.setV("3");
        rules.add(cv);
        itemCreator.setExtList(rules);
        templateItemService.createTemplateItem(itemCreator);
    }

    @Test
    public void testAddTemplateRel(){
        List<TemplateItemRel> relList = Lists.newArrayList();
        TemplateItemRel r1 = new TemplateItemRel();
        r1.setTemplateId(6L);
        r1.setItemId(8L);
        relList.add(r1);
        TemplateItemRel r2 = new TemplateItemRel();
        r2.setTemplateId(6L);
        r2.setItemId(9L);
        relList.add(r2);

        TemplateItemRel r3 = new TemplateItemRel();
        r3.setTemplateId(6L);
        r3.setItemId(10L);
        relList.add(r3);
        templateItemRelRepository.saveAll(relList);
    }


    @Test
    public void testAddGenRule(){
        GenRuleConfigCreator creator = new GenRuleConfigCreator();
        creator.setDescInfo("邮件");
        creator.setName("邮件");
        List<GenRule> list = Lists.newArrayList();
        EmailGenRule emailGenRule = new EmailGenRule(10,"unicom.com",0);
        list.add(emailGenRule);
        creator.setRuleList(list);
        genRuleConfigService.createGenRuleConfig(creator);

        GenRuleConfigCreator creator1 = new GenRuleConfigCreator();
        creator1.setDescInfo("用户名");
        creator1.setName("用户名");
        List<GenRule> list1 = Lists.newArrayList();
        ChineseNameGenRule chineseNameGenRule = new ChineseNameGenRule(2);
        list1.add(chineseNameGenRule);
        creator1.setRuleList(list1);
        genRuleConfigService.createGenRuleConfig(creator1);

    }



    @Test
    public void testAddListRule(){
        GenRuleConfigCreator creator = new GenRuleConfigCreator();
        creator.setDescInfo("最新测试");
        creator.setName("最新测试");
        List<GenRule> list = Lists.newArrayList();
        NestedObjectGenRule nestedObjectGenRule = new NestedObjectGenRule(2L,3);
        nestedObjectGenRule.setListSize(3);
        list.add(nestedObjectGenRule);
        creator.setRuleList(list);
        genRuleConfigService.createGenRuleConfig(creator);
    }

    @Test
    public void testSaveItemGenRel(){
        List<GenRuleItemRel> rels = Lists.newArrayList();
        GenRuleItemRel ruleItemRel = new GenRuleItemRel();
        ruleItemRel.setItemId(8L);
        ruleItemRel.setRuleId(2L);
        rels.add(ruleItemRel);

        GenRuleItemRel ruleItemRel2 = new GenRuleItemRel();
        ruleItemRel2.setItemId(9L);
        ruleItemRel2.setRuleId(1L);
        rels.add(ruleItemRel2);

        GenRuleItemRel ruleItemRel3 = new GenRuleItemRel();
        ruleItemRel3.setItemId(10L);
        ruleItemRel3.setRuleId(3L);
        rels.add(ruleItemRel3);
        genRuleItemRelRepository.saveAll(rels);

    }



    @Test
    public void testAddVerifyRules(){
        VerifyRuleConfigCreator creator = new VerifyRuleConfigCreator();
        creator.setName("校验规则1");
        List<VerifyRule> rules = Lists.newArrayList();
        MaxVerifyRule maxVerifyRule = new MaxVerifyRule();
        maxVerifyRule.setMaxValue(new BigDecimal(100));
        rules.add(maxVerifyRule);
        creator.setRuleList(rules);
        verifyRuleConfigService.createVerifyRuleConfig(creator);
    }


}
