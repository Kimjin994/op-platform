package com.only4play.system.domain.template.mockrule.domainservice;

import java.util.Map;

public interface IMockRuleDomainService {

    /**
     * 根据模板id获取模拟数据
     * @param templateId
     * @return
     */
    Map<String,Object> getMockDataByTemplateId(Long templateId);

    /**
     * 根据模板编码获取模拟数据
     * @param templateCode
     * @return
     */
    Map<String,Object> getMockDataByTemplateCode(String templateCode);
}
