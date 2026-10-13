package com.only4play.system.domain.admin.request;

import com.only4play.common.annotation.FieldDesc;
import com.only4play.common.model.Request;
import lombok.Data;

import java.util.List;

/**
 * @author noon
 * @date 2022/8/31
 */
@Data
public class GrantUserPlatformRequest implements Request {

  @FieldDesc(name = "用户Id")
  private Long userId;

  @FieldDesc(name = "平台Id")
  private List<Long> platformIds;
}
