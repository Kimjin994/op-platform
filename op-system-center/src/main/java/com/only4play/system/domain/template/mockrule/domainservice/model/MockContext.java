package com.only4play.system.domain.template.mockrule.domainservice.model;

import com.only4play.common.annotation.FieldDesc;
import com.only4play.system.domain.template.templateitem.ValueType;
import lombok.Data;

import java.util.Map;

@Data
public class MockContext {

    @FieldDesc(name = "编码")
    private String code;

    private ValueType valueType;

    @FieldDesc(name = "参数")
    private Map<String,Object> params;
}
