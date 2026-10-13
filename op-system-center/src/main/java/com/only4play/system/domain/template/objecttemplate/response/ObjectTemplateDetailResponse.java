package com.only4play.system.domain.template.objecttemplate.response;

import com.only4play.common.model.AbstractJpaResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 模板详情
 */
@Data
public class ObjectTemplateDetailResponse extends AbstractJpaResponse {

  @Schema(
      title = "模板名称"
  )
  private String name;

  @Schema(
      title = "模板编码"
  )
  private String code;

  @Schema(
      title = "创建人"
  )
  private String createUser;

  @Schema(
      title = "模板code"
  )
  private String categoryCode;

  @Schema(
      title = "模板id"
  )
  private Long categoryId;

  @Schema(
      title = "描述信息"
  )
  private String description;

  @Schema(
      title = "validStatus"
  )
  private Integer validStatus;
}
