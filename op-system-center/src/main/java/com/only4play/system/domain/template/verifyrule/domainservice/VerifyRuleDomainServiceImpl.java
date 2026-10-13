package com.only4play.system.domain.template.verifyrule.domainservice;

import com.only4play.common.constants.CodeEnum;
import com.only4play.common.constants.ValidStatus;
import com.only4play.common.exception.BusinessException;
import com.only4play.system.domain.template.objecttemplate.repository.ObjectTemplateRepository;
import com.only4play.system.domain.template.objecttemplate.service.IObjectTemplateService;
import com.only4play.system.domain.template.templateitem.vo.TemplateItemVO;
import com.only4play.system.domain.template.verifyrule.VerifyRuleConfig;
import com.only4play.system.domain.template.verifyrule.repository.VerifyRuleConfigRepository;
import com.only4play.system.domain.template.verifyrule.rule.VerifyRule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyRuleDomainServiceImpl implements IVerifyRuleDomainService {

  private final ObjectTemplateRepository objectTemplateRepository;

  private final IObjectTemplateService objectTemplateService;

  private final VerifyRuleConfigRepository verifyRuleConfigRepository;

  @Override
  public Map<String, List<String>> verify(String templateCode, Map<String, Object> data) {
    objectTemplateRepository.findByCode(templateCode)
        .orElseThrow(() -> new BusinessException(CodeEnum.NotFindError));
    Map<String, Object> payload = data == null ? Collections.emptyMap() : data;
    Map<String, List<String>> errors = new LinkedHashMap<>();
    for (TemplateItemVO item : objectTemplateService.findItemsByTemplateCode(templateCode)) {
      List<String> fieldErrors = new ArrayList<>();
      Object raw = payload.get(item.getCode());
      String input = raw == null ? "" : String.valueOf(raw);
      List<VerifyRuleConfig> configs = verifyRuleConfigRepository
          .findByItemIdAndValidStatus(item.getId(), ValidStatus.VALID);
      for (VerifyRuleConfig config : configs) {
        if (config.getRuleList() == null) {
          continue;
        }
        for (VerifyRule rule : config.getRuleList()) {
          rule.verify(input).ifPresent(fieldErrors::add);
        }
      }
      if (!fieldErrors.isEmpty()) {
        errors.put(item.getCode(), fieldErrors);
      }
    }
    return errors;
  }
}
