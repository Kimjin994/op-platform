package com.only4play.system.domain.template.genrule.rule.genrule;

import com.only4play.common.annotation.FieldDesc;
import com.only4play.common.constants.BaseEnum;
import lombok.Getter;

import java.util.Optional;

public enum GenTypeEnum implements BaseEnum<GenTypeEnum> {

    JOIN_STR(1, "拼接字符串", "prefix_前缀;suffix_后缀", false),
    EMAIL(2, "邮件", "maxlength_最大长度;suffix_后缀", true),
    NUMBER_RANGE(3, "数值范围", "min_最小值;max_最大值", true),
    CHINESE_NAME(4, "中文名字", "", true),
    BOOLEAN_VALUE(5, "BOOLEAN值", "", true),
    DATE(6,"日期","pattern_格式;begin_开始时间;end_结束时间",true),
    NESTED_OBJECT(7,"嵌套对象","templateCode_模板编码",true),
    RANGE_LONG(8,"整数范围","min_最小值;max_最大值",true),
    RANGE_DOUBLE(9,"浮点范围","min_最小值;max_最大值",true),
    USER_PHONE(10,"手机号","",true),
    CAR_NO(11,"车牌号","",true),
    MAP_POINT(12,"地图坐标点","",true)
    ;

    GenTypeEnum(Integer code, String name, String paramKeys, boolean exclusive) {
        this.code = code;
        this.name = name;
        this.paramKeys = paramKeys;
        this.exclusive = exclusive;
    }

    private Integer code;
    private String name;

    @FieldDesc(name = "参数列表")
    @Getter
    private String paramKeys;

    @FieldDesc(name = "是否排它")
    @Getter
    private boolean exclusive;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static Optional<GenTypeEnum> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(GenTypeEnum.class, code));
    }

}
