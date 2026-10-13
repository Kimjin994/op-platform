package com.only4play.system.controller.message;

import com.only4play.common.constants.CodeEnum;
import com.only4play.common.model.JsonObject;
import com.only4play.system.domain.message.messagerecord.MsgTypeEnum;
import com.only4play.system.domain.message.messagerecord.NoticeType;
import com.only4play.system.domain.message.messagerecord.domainservice.IMessageRecordDomainService;
import com.only4play.system.domain.message.messagerecord.domainservice.model.MessageSendModel;
import com.only4play.system.domain.message.messagerecord.request.SendVerifyCodeRequest;
import com.only4play.system.infrastructure.config.MessageProperties;
import com.only4play.system.infrastructure.constants.MessageConstants;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public/message")
@RequiredArgsConstructor
public class PublicMessageController {

  private final IMessageRecordDomainService messageRecordDomainService;

  private final MessageProperties messageProperties;

  /**
   * 发送登录验证码。本期只写入 verify_record，不真实下发。
   */
  @PostMapping("sendVerifyCode")
  public JsonObject<String> sendVerifyCode(@RequestBody SendVerifyCodeRequest request) {
    MessageSendModel model = new MessageSendModel();
    model.setTemplateCode(messageProperties.getLoginTemplateCode());
    model.setMsgType(MsgTypeEnum.VERIFY);
    model.setNoticeType(NoticeType.SMS);
    Map<String, Object> params = new HashMap<>();
    params.put(MessageConstants.ACCOUNT, request.getAccount());
    model.setParams(params);
    messageRecordDomainService.sendMessage(model);
    return JsonObject.success(CodeEnum.Success.getName());
  }
}
