package com.only4play.system.domain.message.messagerecord.request;

import com.only4play.common.model.Request;
import com.only4play.system.domain.message.messagerecord.MsgTypeEnum;
import com.only4play.system.domain.message.messagerecord.NoticeType;
import java.util.Map;
import lombok.Data;

@Data
public class MessageSendRequest implements Request {

  private NoticeType noticeType;

  private String templateCode;

  private Map<String, Object> params;

  private MsgTypeEnum msgType;
}
