package com.only4play.system.domain.template.genrule.response;

import com.only4play.common.model.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GenTypeResponse implements Response {

    @Schema(title = "参数列表")
    private String paramKeys;

    @Schema(title = "是否排它")
    private boolean exclusive;

    @Schema(title = "编码")
    private Integer code;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "文本")
    private String txt;
}
