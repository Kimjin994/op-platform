package com.only4play.system.domain.message.messagerecord.domainservice;

import com.only4play.common.exception.BusinessException;
import com.only4play.jpa.support.EntityOperations;
import com.only4play.system.domain.message.messagerecord.domainservice.model.MessageSendModel;
import com.only4play.system.domain.message.messagerecord.mapper.MessageRecordMapper;
import com.only4play.system.domain.message.messagerecord.repository.MessageRecordRepository;
import com.only4play.system.domain.message.messagetemplate.MessageTemplate;
import com.only4play.system.domain.message.messagetemplate.repository.MessageTemplateRepository;
import com.only4play.system.infrastructure.constants.MessageErrorCode;
import java.util.Optional;
import jodd.util.StringTemplateParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageRecordDomainServiceImpl implements IMessageRecordDomainService{

  private final MessageTemplateRepository messageTemplateRepository;

  private final MessageRecordRepository messageRecordRepository;

  @Override
  public void sendMessage(MessageSendModel messageSendModel) {

    Optional<MessageTemplate> template = messageTemplateRepository.findByTemplateCode(
        messageSendModel.getTemplateCode());
    if(!template.isPresent()){
      throw new BusinessException(MessageErrorCode.TEMPLATE_NOT_FIND);
    }
    //模板内容替换
    String content = StringTemplateParser
        .ofMap(messageSendModel.getParams())
        .apply(template.get().getTemplate());
    EntityOperations
        .doCreate(messageRecordRepository)
        .create(() -> MessageRecordMapper.INSTANCE.model2Entity(messageSendModel))
        .update(e -> e.init(content))
        .execute();
  }
}
