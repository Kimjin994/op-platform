package com.only4play.exam.domain.car;

import com.only4play.codegen.processor.api.GenCreateRequest;
import com.only4play.codegen.processor.api.GenQueryRequest;
import com.only4play.codegen.processor.api.GenResponse;
import com.only4play.codegen.processor.api.GenUpdateRequest;
import com.only4play.codegen.processor.controller.GenController;
import com.only4play.codegen.processor.creator.GenCreator;
import com.only4play.codegen.processor.mapper.GenMapper;
import com.only4play.codegen.processor.query.GenQuery;
import com.only4play.codegen.processor.repository.GenRepository;
import com.only4play.codegen.processor.service.GenService;
import com.only4play.codegen.processor.creator.IgnoreCreator;
import com.only4play.codegen.processor.updater.IgnoreUpdater;
import com.only4play.codegen.processor.service.GenServiceImpl;
import com.only4play.codegen.processor.updater.GenUpdater;
import com.only4play.codegen.processor.vo.GenVo;
import com.only4play.common.annotation.FieldDesc;
import com.only4play.common.annotation.TypeConverter;
import com.only4play.common.constants.ValidStatus;
import com.only4play.jpa.converter.ValidStatusConverter;
import com.only4play.jpa.support.BaseJpaAggregate;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.only4play.exam.domain.car.vo")
@GenCreator(pkgName = "com.only4play.exam.domain.car.creator")
@GenUpdater(pkgName = "com.only4play.exam.domain.car.updater")
@GenRepository(pkgName = "com.only4play.exam.domain.car.repository")
@GenService(pkgName = "com.only4play.exam.domain.car.service")
@GenServiceImpl(pkgName = "com.only4play.exam.domain.car.service")
@GenQuery(pkgName = "com.only4play.exam.domain.car.query")
@GenMapper(pkgName = "com.only4play.exam.domain.car.mapper")
@GenController(pkgName = "com.only4play.exam.controller")
@GenCreateRequest(pkgName = "com.only4play.exam.domain.car.request")
@GenUpdateRequest(pkgName = "com.only4play.exam.domain.car.request")
@GenQueryRequest(pkgName = "com.only4play.exam.domain.car.request")
@GenResponse(pkgName = "com.only4play.exam.domain.car.response")
@Entity
@Table(name = "car")
@Data
public class Car extends BaseJpaAggregate {

  @FieldDesc(name = "车牌号")
  private String platNum;

  @FieldDesc(name = "唯一编码")
  private String code;

  @FieldDesc(name = "颜色")
  private String color;


  @Convert(converter = ValidStatusConverter.class)
  @IgnoreUpdater
  @IgnoreCreator
  @TypeConverter(toTypeFullName = "java.lang.Integer")
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
