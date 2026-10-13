package com.only4play.system.domain.message.messagerecord.events;

import com.only4play.system.domain.message.messagerecord.MessageRecord;
import lombok.Value;

public interface MessageRecordEvents {

  @Value
  class MessageRecordCreateEvent{
    private MessageRecord messageRecord;
  }

}
