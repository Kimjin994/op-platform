package com.only4play.system.domain.message.messagerecord;

import com.only4play.codegen.processor.creator.GenCreator;
import com.only4play.codegen.processor.creator.IgnoreCreator;
import com.only4play.codegen.processor.query.GenQuery;
import com.only4play.codegen.processor.updater.GenUpdater;
import com.only4play.codegen.processor.updater.IgnoreUpdater;
import com.only4play.codegen.processor.vo.GenVo;
import com.only4play.common.annotation.FieldDesc;
import com.only4play.common.annotation.TypeConverter;
import com.only4play.common.constants.ValidStatus;
import com.only4play.jpa.converter.ValidStatusConverter;
import com.only4play.jpa.support.BaseJpaAggregate;
import com.only4play.system.domain.message.messagerecord.events.MessageRecordEvents.MessageRecordCreateEvent;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.only4play.system.domain.message.messagerecord.vo")
@GenCreator(pkgName = "com.only4play.system.domain.message.messagerecord.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.message.messagerecord.updater")
@GenQuery(pkgName = "com.only4play.system.domain.message.messagerecord.query")
@Entity
@Table(name = "message_record")
@Data
public class MessageRecord extends BaseJpaAggregate {

  @FieldDesc(name = "模板编码")
  private String templateCode;

  @Column(columnDefinition = "text")
  private String content;

  @FieldDesc(name = "发送参数")
  private String params;

  @FieldDesc(name = "消息类型")
  @Convert(converter = MsgTypeEnumConverter.class)
  @TypeConverter(toTypeFullName = "java.lang.Integer")
  private MsgTypeEnum msgType;

  @Convert(converter = NoticeTypeConverter.class)
  @TypeConverter(toTypeFullName = "java.lang.Integer")
  private NoticeType noticeType;

  @Convert(converter = ValidStatusConverter.class)
  @IgnoreUpdater
  @IgnoreCreator
  private ValidStatus validStatus;

  /**
   * 初始化发送事件
   * @param content
   */
  public void init(String content) {
    setContent(content);
    setValidStatus(ValidStatus.VALID);
    registerEvent(new MessageRecordCreateEvent(this));
  }

  public void valid(){
    setValidStatus(ValidStatus.VALID);
  }

  public void invalid(){
    setValidStatus(ValidStatus.INVALID);
  }
}
