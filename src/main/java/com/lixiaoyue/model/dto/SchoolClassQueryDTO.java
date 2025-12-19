package com.lixiaoyue.model.dto;

import com.lixiaoyue.common.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class SchoolClassQueryDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "班级名称")
    private String schoolClassName ;
    @Schema(description = "科目")
    private String courseName;
    @Schema(description = "课程id")
    private Long courseId;
}
