package com.only4play.system.domain.message.verifyrecord.service.checker;

import cn.hutool.extra.spring.SpringUtil;
import com.only4play.system.domain.message.verifyrecord.service.IVerifyRecordService;
import net.bytebuddy.matcher.ElementMatcher;

public class SendMaxTimesChecker implements ElementMatcher<CheckContext> {

  @Override
  public boolean matches(CheckContext checkContext) {
    IVerifyRecordService recordService = SpringUtil.getBean(IVerifyRecordService.class);
    return recordService.checkSendMaxTimes(checkContext.getAccount(), checkContext.getTemplateCode());
  }
}
