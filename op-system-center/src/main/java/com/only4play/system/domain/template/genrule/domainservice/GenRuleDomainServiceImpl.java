package com.only4play.system.domain.template.genrule.domainservice;

import com.google.common.collect.Maps;
import com.only4play.common.constants.CodeEnum;
import com.only4play.common.exception.BusinessException;
import com.only4play.system.domain.template.genrule.rule.genrule.GenRule;
import com.only4play.system.domain.template.objecttemplate.repository.ObjectTemplateRepository;
import com.only4play.system.domain.template.objecttemplate.service.IObjectTemplateService;
import com.only4play.system.domain.template.templateitem.service.ITemplateItemService;
import com.only4play.system.domain.template.templateitem.vo.TemplateItemVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GenRuleDomainServiceImpl implements IGenRuleDomainService{

    private final ITemplateItemService templateItemService;

    private final IObjectTemplateService templateService;

    private final ObjectTemplateRepository objectTemplateRepository;

    @Override
    public Map<String, Object> genJsonObjectMapByTemplateId(Long templateId) {
        List<TemplateItemVO> itemVos = templateService.findItemsByTemplateId(templateId);
        Map<String,Object> result = Maps.newHashMap();
        itemVos.stream()
                .forEach(itemVo -> {
                    List<GenRule> genRules = templateItemService.getRulesByTemplateItemId(itemVo.getId());
                    Map<String,Object> temp = Maps.newHashMap();
                    for(GenRule gr : genRules){
                        Map<String, Object> now = gr.getGenData(itemVo.getCode(), temp, itemVo.getValueType());
                        result.putAll(now);
                        temp = now;
                    }
                });
        return result;
    }

    @Override
    public Map<String, Object> genJsonObjectMapByTemplateCode(String templateCode) {
        Long templateId = objectTemplateRepository.findByCode(templateCode)
                .orElseThrow(() -> new BusinessException(CodeEnum.NotFindError))
                .getId();
        return genJsonObjectMapByTemplateId(templateId);
    }
}
