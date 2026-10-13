package com.only4play.system.domain.message.verifyrecord;

import com.only4play.codegen.processor.creator.GenCreator;
import com.only4play.codegen.processor.creator.IgnoreCreator;
import com.only4play.codegen.processor.query.GenQuery;
import com.only4play.codegen.processor.updater.GenUpdater;
import com.only4play.codegen.processor.updater.IgnoreUpdater;
import com.only4play.codegen.processor.vo.GenVo;
import com.only4play.common.annotation.FieldDesc;
import com.only4play.common.constants.ValidStatus;
import com.only4play.jpa.converter.ValidStatusConverter;
import com.only4play.jpa.support.BaseJpaAggregate;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.only4play.system.domain.message.verifyrecord.vo")
@GenCreator(pkgName = "com.only4play.system.domain.message.verifyrecord.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.message.verifyrecord.updater")
@GenQuery(pkgName = "com.only4play.system.domain.message.verifyrecord.query")
@Entity
@Table(name = "verify_record")
@Data
public class VerifyRecord extends BaseJpaAggregate {

  @FieldDesc(name = "账号")
  private String account;

  @FieldDesc(name = "发送内容")
  private String content;

  @FieldDesc(name = "有效期")
  private Long endTime;

  @FieldDesc(name = "验证码")
  private String verifyCode;

  @FieldDesc(name = "模板编码")
  private String templateCode;

  @Convert(converter = ValidStatusConverter.class)
  @IgnoreUpdater
  @IgnoreCreator
  private ValidStatus validStatus;

  public void init() {
    setValidStatus(ValidStatus.VALID);
  }

  public void valid(){
    setValidStatus(ValidStatus.VALID);
  }

  public void invalid(){
    setValidStatus(ValidStatus.INVALID);
  }
}
