package com.lixiaoyue.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SchoolClassJoinDTO implements Serializable {

    Long userId;
    Long schoolClassId;
}
