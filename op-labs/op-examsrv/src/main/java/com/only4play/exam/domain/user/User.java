package com.only4play.exam.domain.user;

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

@GenVo(pkgName = "com.only4play.exam.domain.user.vo")
@GenCreator(pkgName = "com.only4play.exam.domain.user.creator")
@GenUpdater(pkgName = "com.only4play.exam.domain.user.updater")
@GenQuery(pkgName = "com.only4play.exam.domain.user.query")
@Entity
@Table(name = "user")
@Data
public class User extends BaseJpaAggregate {

  @FieldDesc(name = "准考证号")
  private String examNo;

  @FieldDesc(name = "真实姓名")
  private String realName;

  @FieldDesc(name = "生日")
  private Long birthDay;

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
