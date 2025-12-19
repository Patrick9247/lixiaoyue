package com.lixiaoyue.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationStatusEnum {


    PASS(0,"通过"),

    FAIL_PASS(1,"未通过"),
    PENDING(3,"待处理");

    /**
     * 状态编码
     */
    private final Integer code;
    /**
     * 描述
     */
    private final String desc;


}
