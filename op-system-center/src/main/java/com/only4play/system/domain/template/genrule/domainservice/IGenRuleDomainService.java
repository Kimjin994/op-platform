package com.only4play.system.domain.template.genrule.domainservice;

import java.util.Map;

public interface IGenRuleDomainService {

    Map<String,Object> genJsonObjectMapByTemplateId(Long templateId);
}
