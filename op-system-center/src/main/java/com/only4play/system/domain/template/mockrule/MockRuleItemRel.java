package com.only4play.system.domain.template.mockrule;

import com.only4play.codegen.processor.repository.GenRepository;
import com.only4play.jpa.support.BaseJpaAggregate;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;

@GenRepository(pkgName = "com.only4play.system.domain.template.mockrule.repository")
@Entity
@Table(name = "mock_rule_item_rel")
@Data
public class MockRuleItemRel extends BaseJpaAggregate {

 private Long mockRuleId;

 private Long itemId;
}
