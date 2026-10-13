package com.only4play.exam.domain.casegroup.model;

import com.only4play.common.annotation.FieldDesc;
import lombok.Data;

@Data
public class TaskGroupCreateModel {

  @FieldDesc(name = "caseGroupId")
  private Long caseGroupId;

  @FieldDesc(name = "任务组名称")
  private String name;

  @FieldDesc(name = "批次号")
  private String batchNo;

  @FieldDesc(name = "任务开始时间")
  private Long startTime;

  @FieldDesc(name = "结束时间")
  private Long endTime;

}
