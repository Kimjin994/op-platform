package com.only4play.system.domain.permission.resource.mapper;

import com.only4play.system.domain.permission.resource.ResourceType;

/**
 * @author noon
 * @date 2022/8/30
 */
public class ResourceEnumMapper {

  public Integer asInteger(ResourceType type) {
    return type.getCode();
  }

  public ResourceType asResourceType(Integer code) {
    return ResourceType.of(code).orElse(ResourceType.MODULE);
  }
}
