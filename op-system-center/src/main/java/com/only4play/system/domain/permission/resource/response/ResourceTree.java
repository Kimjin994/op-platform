package com.only4play.system.domain.permission.resource.response;

import com.google.common.collect.Lists;
import com.only4play.common.model.AbstractJpaResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author gim
 */
@Data
public class ResourceTree extends AbstractJpaResponse {

  private List<ResourceTree> children = Lists.newArrayList();

  @Schema(
      title ="排序号")
  private Integer sortNum;

  @Schema(
      title ="pidName")
  private String pidName;

  @Schema(
      title ="资源描述")
  private String resourceDesc;

  @Schema(
      title ="禁用状态")
  private Integer validStatus;

  @Schema(
      title ="父节点")
  private Long pid;

  @Schema(
      title ="资源类型")
  private Integer resourceType;

  @Schema(
      title ="资源名称")
  private String name;

  @Schema(
      title ="icon class")
  private String iconClass;

  @Schema(
      title ="平台id")
  private Long platformId;

  @Schema(
      title ="资源链接")
  private String url;

  @Schema(
      title ="资源编码")
  private String code;

  @Schema(
      title ="前端路由")
  private String router;

}
