package com.only4play.system.infrastructure.utils;

import com.google.common.collect.Streams;
import com.only4play.system.domain.permission.resource.Resource;
import com.only4play.system.domain.permission.resource.mapper.ResourceMapper;
import com.only4play.system.domain.permission.resource.response.ResourceTree;
import com.only4play.system.infrastructure.constants.AuthConstants;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 资源树util
 * @author admin
 */
public enum ResourceUtil {
  ;
  /**
   * 资源转换为树形结构
   */
  public static List<ResourceTree> resourceList2ResourceTree(Iterable<Resource> list) {
    List<ResourceTree> parentList = Streams.stream(list)
        .filter(r -> AuthConstants.ROOT_RESOURCE_ID.equals(r.getPid()))
        .collect(Collectors.toList())
        .stream()
        .map(ResourceMapper.INSTANCE::entityToTree)
        .collect(Collectors.toList());
    setChild(parentList, list);
    return parentList;
  }

  /**
   * 递归调用组装树形结构
   */
  public static void setChild(List<ResourceTree> parent, Iterable<Resource> all) {
    if (!CollectionUtils.isEmpty(parent)) {
      parent.forEach(pt -> {
        List<ResourceTree> next = Streams.stream(all)
            .filter(r -> r.getPid().equals(pt.getId()))
            .collect(Collectors.toList())
            .stream()
            .map(ResourceMapper.INSTANCE::entityToTree)
            .collect(Collectors.toList());
        pt.setChildren(next);
        setChild(next, all);
      });
    }
  }

  }
