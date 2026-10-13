package com.only4play.system.domain.template.verifyrule.domainservice;

import java.util.List;
import java.util.Map;

public interface IVerifyRuleDomainService {

  /**
   * 按模板编码校验数据，返回字段到错误信息的映射。没有错误的字段不出现。
   */
  Map<String, List<String>> verify(String templateCode, Map<String, Object> data);
}
