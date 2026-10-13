package com.only4play.system.domain.message.messagerecord.domainservice;

import com.only4play.system.domain.message.messagerecord.domainservice.model.MessageSendModel;

public interface IMessageRecordDomainService {

  /**
   * 发送消息
   * @param messageSendModel
   */
  void sendMessage(MessageSendModel messageSendModel);

}
