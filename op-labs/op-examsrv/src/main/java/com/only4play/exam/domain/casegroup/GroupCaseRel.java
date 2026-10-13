package com.only4play.exam.domain.casegroup;

import com.only4play.common.annotation.FieldDesc;
import com.only4play.jpa.support.BaseJpaAggregate;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "group_case_rel")
@Data
public class GroupCaseRel extends BaseJpaAggregate {

  @FieldDesc(name = "组Id")
  private Long groupId;

  @FieldDesc(name = "用例Id")
  private Long caseId;

}
