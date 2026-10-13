package com.only4play.system.domain.template.mockrule.domainservice.model;

import com.only4play.common.annotation.FieldDesc;
import com.only4play.system.infrastructure.model.CodeValue;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 生成配置
 */
@Data
public class MockConfig {

    @FieldDesc(name = "生成规则名称")
    private String name;

    @FieldDesc(name = "编码")
    private String code;

    @FieldDesc(name = "参数描述")
    private List<CodeValue> params;

    @FieldDesc(name = "排序号")
    private Integer sortNum = 100;

    @FieldDesc(name = "扩展")
    private Map<String,Object> ext;


}
