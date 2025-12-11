package com.lixiaoyue.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HomeworkStatusEnum {


    SUBMITTED(0,"已提交"),

    UNSUBMITTED(1,"未提交");

    /**
     * 角色编码
     */
    private final Integer code;
    /**
     * 描述
     */
    private final String desc;


}
