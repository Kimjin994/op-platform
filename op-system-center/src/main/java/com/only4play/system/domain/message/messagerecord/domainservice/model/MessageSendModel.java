package com.only4play.system.domain.message.messagerecord.domainservice.model;

import com.only4play.common.annotation.FieldDesc;
import com.only4play.system.domain.message.messagerecord.MsgTypeEnum;
import com.only4play.system.domain.message.messagerecord.NoticeType;
import java.util.Map;
import lombok.Data;

@Data
public class MessageSendModel {

  @FieldDesc(name = "通知类型")
  private NoticeType noticeType;

  @FieldDesc(name = "模板编码")
  private String templateCode;

  @FieldDesc(name = "发送参数")
  private Map<String,Object> params;

  @FieldDesc(name = "消息类型")
  private MsgTypeEnum msgType;

}
