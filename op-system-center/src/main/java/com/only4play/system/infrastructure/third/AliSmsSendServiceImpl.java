package com.only4play.system.infrastructure.third;

import com.only4play.extension.executor.Extension;
import lombok.extern.slf4j.Slf4j;

/**
 * 阿里云短信扩展点。待接入阿里云 SDK：补签名、模板编号、手机号和参数。
 * 新短信平台另写一个 @Extension 实现，不改调用方。
 */
@Slf4j
@Extension(bizId = SmsBizId.ALI_SMS_BIZ)
public class AliSmsSendServiceImpl implements ISmsSendService{

  @Override
  public boolean sendSms(SmsSendModel sendModel) {
    log.warn("短信未接入，AliSmsSendServiceImpl 未调用短信平台");
    return false;
  }
}
