package com.only4play.exam.domain.casegroup.model;

import java.util.List;
import lombok.Data;

@Data
public class CaseGroupCreateModel {

  private String name;

  private List<Long> caseIds;

}
