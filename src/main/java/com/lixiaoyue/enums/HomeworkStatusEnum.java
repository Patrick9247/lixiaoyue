package com.lixiaoyue.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HomeworkStatusEnum {


    UNSUBMITTED(0,"未提交"),

    AI_CORRECT(1,"AI已经批改"),

    CONFIRM(2,"教师确认批改");


    /**
     * 角色编码
     */
    private final Integer code;
    /**
     * 描述
     */
    private final String desc;


}
