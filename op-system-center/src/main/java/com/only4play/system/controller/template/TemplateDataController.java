package com.only4play.system.controller.template;

import com.only4play.common.model.JsonObject;
import com.only4play.system.domain.template.genrule.domainservice.IGenRuleDomainService;
import com.only4play.system.domain.template.mockrule.domainservice.IMockRuleDomainService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("template/data/v1")
@RequiredArgsConstructor
public class TemplateDataController {

  private final IGenRuleDomainService genRuleDomainService;

  private final IMockRuleDomainService mockRuleDomainService;

  @GetMapping("gen/{templateId}")
  public JsonObject<Map<String, Object>> gen(@PathVariable Long templateId) {
    return JsonObject.success(genRuleDomainService.genJsonObjectMapByTemplateId(templateId));
  }

  @GetMapping("gen/code/{templateCode}")
  public JsonObject<Map<String, Object>> genByCode(@PathVariable String templateCode) {
    return JsonObject.success(genRuleDomainService.genJsonObjectMapByTemplateCode(templateCode));
  }

  @GetMapping("mock/{templateId}")
  public JsonObject<Map<String, Object>> mock(@PathVariable Long templateId) {
    return JsonObject.success(mockRuleDomainService.getMockDataByTemplateId(templateId));
  }

  @GetMapping("mock/code/{templateCode}")
  public JsonObject<Map<String, Object>> mockByCode(@PathVariable String templateCode) {
    return JsonObject.success(mockRuleDomainService.getMockDataByTemplateCode(templateCode));
  }
}
