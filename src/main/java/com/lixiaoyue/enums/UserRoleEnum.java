package com.lixiaoyue.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleEnum {


    TEACHER("STU","教师"),

    STUDENT("TEC","学生");

    /**
     * 角色编码
     */
    private final String name;
    /**
     * 描述
     */
    private final String desc;


}
