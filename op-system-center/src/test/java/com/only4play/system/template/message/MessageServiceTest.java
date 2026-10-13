package com.only4play.system.template.message;

import com.google.common.collect.Maps;
import com.only4play.system.domain.message.messagerecord.MsgTypeEnum;
import com.only4play.system.domain.message.messagerecord.NoticeType;
import com.only4play.system.domain.message.messagerecord.domainservice.IMessageRecordDomainService;
import com.only4play.system.domain.message.messagerecord.domainservice.model.MessageSendModel;
import com.only4play.system.domain.message.messagetemplate.TemplateType;
import com.only4play.system.domain.message.messagetemplate.creator.MessageTemplateCreator;
import com.only4play.system.domain.message.messagetemplate.service.IMessageTemplateService;
import com.only4play.system.infrastructure.constants.MessageConstants;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MessageServiceTest {

  @Autowired
  private IMessageRecordDomainService messageRecordDomainService;

  @Autowired
  private IMessageTemplateService messageTemplateService;

  @Test
  public void testAddTemplate(){
    MessageTemplateCreator templateCreator = new MessageTemplateCreator();
    templateCreator.setTemplate("发送短信，验证码为:${verifyCode}");
    templateCreator.setTemplateCode("sms_register");
    templateCreator.setName("短信注册");
    templateCreator.setTemplateType(TemplateType.SMS);
    templateCreator.setDescription("短信注册模板");
    messageTemplateService.createMessageTemplate(templateCreator);
  }


  @Test
  public void testSmsSend(){
    MessageSendModel messageSendModel = new MessageSendModel();
    messageSendModel.setNoticeType(NoticeType.SMS);
    messageSendModel.setMsgType(MsgTypeEnum.VERIFY);
    messageSendModel.setTemplateCode("sms_register");
    Map<String,Object> params = Maps.newHashMap();
    params.put(MessageConstants.ACCOUNT,"13888888888");
    messageSendModel.setParams(params);
    messageRecordDomainService.sendMessage(messageSendModel);
  }

}
