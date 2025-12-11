package com.lixiaoyue.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CourseStatusEnum {


    ONLINE(0,"在线"),

    OFFLINE(1,"结课");

    /**
     * 角色编码
     */
    private final Integer code;
    /**
     * 描述
     */
    private final String desc;


}
