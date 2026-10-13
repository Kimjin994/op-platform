package com.only4play.system.domain.template.genrule.rule.genrule;

import cn.hutool.extra.spring.SpringUtil;
import com.only4play.system.domain.template.genrule.domainservice.IGenRuleDomainService;
import com.only4play.system.domain.template.templateitem.ValueType;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

/**
 * 嵌套对象生成器
 * 核心在于拿到模板对象，这样就能继续循环生成了
 */
public class NestedObjectGenRule extends AbstractGenRule{

    @Getter
    @Setter
    private Long templateId;

    @Getter
    @Setter
    private Integer listSize = 1;

    public NestedObjectGenRule(Long templateId,Integer listSize) {
        super(GenTypeEnum.NESTED_OBJECT);
        this.templateId = templateId;
        this.listSize = listSize;
    }

    @Override
    public boolean support(ValueType valueType) {
        return Objects.equals(ValueType.NESTED_OBJECT,valueType);
    }

    @Override
    public Object process(String property, Map<String, Object> lastMap) {
        IGenRuleDomainService genRuleDomainService = SpringUtil.getBean(IGenRuleDomainService.class);
        Map<String, Object> result = genRuleDomainService.genJsonObjectMapByTemplateId(templateId);
        return result;
    }

    @Override
    public Integer getListSize() {
        return this.listSize;
    }
}
