package com.only4play.system.controller.template;

import org.springframework.security.access.prepost.PreAuthorize;
import com.only4play.common.model.JsonObject;
import com.only4play.system.domain.template.genrule.domainservice.IGenRuleDomainService;
import com.only4play.system.domain.template.mockrule.domainservice.IMockRuleDomainService;
import com.only4play.system.domain.template.verifyrule.domainservice.IVerifyRuleDomainService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("template/data/v1")
@RequiredArgsConstructor
public class TemplateDataController {

  private final IGenRuleDomainService genRuleDomainService;

  private final IMockRuleDomainService mockRuleDomainService;

  private final IVerifyRuleDomainService verifyRuleDomainService;

  @PreAuthorize("@perm.has('template:data:gen')")
  @GetMapping("gen/{templateId}")
  public JsonObject<Map<String, Object>> gen(@PathVariable Long templateId) {
    return JsonObject.success(genRuleDomainService.genJsonObjectMapByTemplateId(templateId));
  }

  @PreAuthorize("@perm.has('template:data:gen')")
  @GetMapping("gen/code/{templateCode}")
  public JsonObject<Map<String, Object>> genByCode(@PathVariable String templateCode) {
    return JsonObject.success(genRuleDomainService.genJsonObjectMapByTemplateCode(templateCode));
  }

  @PreAuthorize("@perm.has('template:data:mock')")
  @GetMapping("mock/{templateId}")
  public JsonObject<Map<String, Object>> mock(@PathVariable Long templateId) {
    return JsonObject.success(mockRuleDomainService.getMockDataByTemplateId(templateId));
  }

  @PreAuthorize("@perm.has('template:data:mock')")
  @GetMapping("mock/code/{templateCode}")
  public JsonObject<Map<String, Object>> mockByCode(@PathVariable String templateCode) {
    return JsonObject.success(mockRuleDomainService.getMockDataByTemplateCode(templateCode));
  }

  @PreAuthorize("@perm.has('template:data:verify')")
  @PostMapping("verify/{templateCode}")
  public JsonObject<Map<String, List<String>>> verify(@PathVariable String templateCode,
      @RequestBody Map<String, Object> data) {
    return JsonObject.success(verifyRuleDomainService.verify(templateCode, data));
  }
}
