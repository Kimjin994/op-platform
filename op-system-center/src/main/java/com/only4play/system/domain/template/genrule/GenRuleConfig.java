package com.only4play.system.domain.template.genrule;
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
import com.only4play.common.constants.ValidStatus;
import com.only4play.jpa.converter.ValidStatusConverter;
import com.only4play.jpa.support.BaseJpaAggregate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.only4play.system.domain.template.genrule.rule.genrule.GenRule;
import com.only4play.system.infrastructure.converter.GenRuleListConverter;
import lombok.Data;

import java.util.List;

@GenVo(pkgName = "com.only4play.system.domain.template.genrule.vo")
@GenCreator(pkgName = "com.only4play.system.domain.template.genrule.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.template.genrule.updater")
@GenRepository(pkgName = "com.only4play.system.domain.template.genrule.repository")
@GenService(pkgName = "com.only4play.system.domain.template.genrule.service")
@GenServiceImpl(pkgName = "com.only4play.system.domain.template.genrule.service")
@GenQuery(pkgName = "com.only4play.system.domain.template.genrule.query")
@GenMapper(pkgName = "com.only4play.system.domain.template.genrule.mapper")
@GenController(pkgName = "com.only4play.system.controller")
@GenCreateRequest(pkgName = "com.only4play.system.domain.template.genrule.request")
@GenUpdateRequest(pkgName = "com.only4play.system.domain.template.genrule.request")
@GenQueryRequest(pkgName = "com.only4play.system.domain.template.genrule.request")
@GenResponse(pkgName = "com.only4play.system.domain.template.genrule.response")
@Entity
@Table(name = "gen_rule_config")
@Data
public class GenRuleConfig extends BaseJpaAggregate {

    @FieldDesc(name = "描述信息")
    private String descInfo;

    @FieldDesc(name = "规则名称")
    private String name;

    @FieldDesc(name = "生成规则列表")
    @Convert(converter = GenRuleListConverter.class)
    @Column(columnDefinition = "text")
    private List<GenRule> ruleList;


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
