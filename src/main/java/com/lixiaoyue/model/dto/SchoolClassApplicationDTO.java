package com.lixiaoyue.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SchoolClassApplicationDTO implements Serializable {

    Long userId;
    String userName;
    Long schoolClassId;
}
