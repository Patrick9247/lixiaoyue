package com.lixiaoyue.model.dto;

import com.lixiaoyue.common.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class SchoolClassStudentQueryDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "学生姓名")
    private String studentName ;
    @Schema(description = "学号")
    private String stuID;
    @Schema(description = "班级id")
    private Long schoolClassId;
}
