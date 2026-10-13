package com.only4play.system.domain.permission.role.request;

import com.only4play.common.annotation.FieldDesc;
import com.only4play.common.model.Request;
import lombok.Data;

import java.util.List;

/**
 * @author noon
 * @date 2022/8/31
 */
@Data
public class GrantRoleResourcesRequest implements Request {

  @FieldDesc(name = "角色id")
  private Long roleId;

  @FieldDesc(name = "资源ids")
  private List<Long> resourceIds;
}
