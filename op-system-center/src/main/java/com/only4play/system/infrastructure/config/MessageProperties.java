package com.only4play.system.infrastructure.config;


import com.only4play.common.annotation.FieldDesc;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "op.message")
@Data
public class MessageProperties {

  @FieldDesc(name = "验证码长度")
  private Integer verifyLength;

  @FieldDesc(name = "有效的时长(分钟)")
  private Integer validTime;

  @FieldDesc(name = "发送间隔(分钟)")
  private Integer sendInterval;

  @FieldDesc(name = "某类短信当天发送最多次数(根据模板判断)")
  private Integer sendMaxTimes;


}
