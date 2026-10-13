package com.only4play.system.domain.message.messagerecord.events;


import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.only4play.common.exception.BusinessException;
import com.only4play.extension.executor.ServiceExecutor;
import com.only4play.system.domain.message.messagerecord.MessageRecord;
import com.only4play.system.domain.message.messagerecord.MsgTypeEnum;
import com.only4play.system.domain.message.messagerecord.NoticeType;
import com.only4play.system.domain.message.messagerecord.events.MessageRecordEvents.MessageRecordCreateEvent;
import com.only4play.system.domain.message.verifyrecord.creator.VerifyRecordCreator;
import com.only4play.system.domain.message.verifyrecord.service.IVerifyRecordService;
import com.only4play.system.domain.message.verifyrecord.service.checker.CheckContext;
import com.only4play.system.domain.message.verifyrecord.service.checker.SendIntervalChecker;
import com.only4play.system.domain.message.verifyrecord.service.checker.SendMaxTimesChecker;
import com.only4play.system.infrastructure.config.MessageProperties;
import com.only4play.system.infrastructure.constants.MessageConstants;
import com.only4play.system.infrastructure.constants.MessageErrorCode;
import com.only4play.system.infrastructure.third.ISmsSendService;
import com.only4play.system.infrastructure.third.SmsSendModel;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.matcher.ElementMatchers;
import org.apache.commons.collections.MapUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageRecordEventProcessor {

  private final ServiceExecutor serviceExecutor;

  private final IVerifyRecordService verifyRecordService;

  private final MessageProperties messageProperties;

  /**
   * 如果是验证消息 单独存在验证记录里
   * @param createEvent
   */
  @EventListener
  public void handMessageRecordForSaveVerifyRecord(MessageRecordCreateEvent createEvent){
    MsgTypeEnum msgType = createEvent.getMessageRecord().getMsgType();
    if(Objects.equals(MsgTypeEnum.VERIFY,msgType)){
      // 加上验证策略，可不可以发，发送间隔 ，发送策略属于什么问题？ 是否问题，是否问题用什么扩展， Matcher
      //如果是验证码，存储验证记录
      CheckContext checkContext = new CheckContext();
      Map<String, Object> params = JSONObject.parseObject(createEvent.getMessageRecord().getParams());
      checkContext.setAccount(MapUtils.getString(params, MessageConstants.ACCOUNT));
      checkContext.setTemplateCode(createEvent.getMessageRecord().getTemplateCode());
      boolean checkResult = ElementMatchers.any()
          .and(new SendIntervalChecker())
          .and(new SendMaxTimesChecker())
          .matches(checkContext);
      if(!checkResult){
        log.error("发送频繁");
        throw new BusinessException(MessageErrorCode.MESSAGE_SEND_FAST);
      }
      VerifyRecordCreator creator = new VerifyRecordCreator();
      String verifyCode = RandomUtil.randomNumbers(messageProperties.getVerifyLength());
      creator.setAccount(MapUtils.getString(params, MessageConstants.ACCOUNT));
      creator.setTemplateCode(createEvent.getMessageRecord().getTemplateCode());
      creator.setContent(createEvent.getMessageRecord().getContent());
      creator.setVerifyCode(verifyCode);
      creator.setEndTime(Instant.now().plus(messageProperties.getValidTime(), ChronoUnit.MINUTES).toEpochMilli());
      verifyRecordService.createVerifyRecord(creator);
      log.info("验证码已写入 verify_record，本期未真实下发，account={}, templateCode={}",
          creator.getAccount(), creator.getTemplateCode());
    }
  }

  /***
   * 处理钉钉消息  这里根据实际情况可以选择异步
   * @param createEvent
   */
  @EventListener
  public void handleMessageForDing(MessageRecordCreateEvent createEvent){
    NoticeType noticeType = createEvent.getMessageRecord().getNoticeType();
    MsgTypeEnum msgType = createEvent.getMessageRecord().getMsgType();
    //是通知的话进行处理
    if(Objects.equals(NoticeType.DING_DING,noticeType) && Objects.equals(MsgTypeEnum.NOTICE,msgType)){

    }
  }

  /**
   * 处理短信消息
   * @param createEvent
   */
  @EventListener
  public void handleMessageForSms(MessageRecordCreateEvent createEvent){
    NoticeType noticeType = createEvent.getMessageRecord().getNoticeType();
    MsgTypeEnum msgType = createEvent.getMessageRecord().getMsgType();
    if(Objects.equals(NoticeType.SMS,noticeType) && Objects.equals(MsgTypeEnum.NOTICE,msgType)){
      MessageRecord messageRecord = createEvent.getMessageRecord();
      SmsSendModel ssm = new SmsSendModel();
      //设置参数
      Boolean sendResult = serviceExecutor.execute(ISmsSendService.class, SmsBiz.ALI, f -> {
        return f.sendSms(ssm);
      });
    }
  }

  /**
   * 处理邮件消息
   * @param createEvent
   */
  @EventListener
  public void handleMessageForEmail(MessageRecordCreateEvent createEvent){

  }


}
