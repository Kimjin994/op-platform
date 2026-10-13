package com.only4play.system.domain.template.genrule;

import com.only4play.jpa.support.BaseJpaAggregate;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "gen_rule_item_rel")
@Data
public class GenRuleItemRel extends BaseJpaAggregate {

  private Long ruleId;

  private Long itemId;

}
