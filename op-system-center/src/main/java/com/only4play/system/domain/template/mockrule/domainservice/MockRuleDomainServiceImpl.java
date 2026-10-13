package com.only4play.system.domain.template.mockrule.domainservice;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.only4play.system.domain.template.mockrule.domainservice.model.MockContext;
import com.only4play.system.domain.template.mockrule.domainservice.plugin.IMockPlugin;
import com.only4play.system.domain.template.mockrule.service.IMockRuleConfigService;
import com.only4play.system.domain.template.mockrule.vo.MockRuleConfigVO;
import com.only4play.system.domain.template.objecttemplate.service.IObjectTemplateService;
import com.only4play.system.domain.template.templateitem.vo.TemplateItemVO;
import lombok.RequiredArgsConstructor;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MockRuleDomainServiceImpl implements IMockRuleDomainService{

    private final PluginRegistry<IMockPlugin, MockContext> registry;

    private final IObjectTemplateService templateService;

    private final IMockRuleConfigService mockRuleConfigService;

    @Override
    public Map<String, Object> getMockDataByTemplateId(Long templateId) {
        List<TemplateItemVO> itemVos = templateService.findItemsByTemplateId(templateId);
        Map<String,Object> result = Maps.newHashMap();
        itemVos.stream()
                .forEach(itemVo -> {
                    List<MockRuleConfigVO> mockRules = mockRuleConfigService.getMockRulesByItemId(itemVo.getId());
                    mockRules.stream()
                            .forEach(vo -> {
                                MockContext mockContext = new MockContext();
                                mockContext.setCode(vo.getCode());
                                Map<String,Object> params = JSON.parseObject(vo.getParams(), Map.class);
                                mockContext.setParams(params);
                                Optional<IMockPlugin> plugin = registry.getPluginFor(mockContext);
                                plugin.ifPresent(p -> {
                                    Map<String, Object> itemMockData = p.getGenData(vo.getCode(), params);
                                    result.putAll(itemMockData);
                                });
                            });
                });
        return result;
    }

    @Override
    public Map<String, Object> getMockDateByTemplateCode(String templateCode) {
        List<TemplateItemVO> itemVos = templateService.findItemsByTemplateCode(templateCode);
        Map<String,Object> result = Maps.newHashMap();
        itemVos.stream()
                .forEach(itemVo -> {
                    List<MockRuleConfigVO> mockRules = mockRuleConfigService.getMockRulesByItemId(itemVo.getId());
                    mockRules.stream()
                            .forEach(vo -> {
                                MockContext mockContext = new MockContext();
                                mockContext.setCode(vo.getCode());
                                Map<String,Object> params = JSON.parseObject(vo.getParams(), Map.class);
                                mockContext.setParams(params);
                                Optional<IMockPlugin> plugin = registry.getPluginFor(mockContext);
                                plugin.ifPresent(p -> {
                                    Map<String, Object> itemMockData = p.getGenData(vo.getCode(), params);
                                    result.putAll(itemMockData);
                                });
                            });
                });
        return result;
    }
}
