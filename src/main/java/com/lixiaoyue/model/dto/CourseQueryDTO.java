package com.lixiaoyue.model.dto;

import com.lixiaoyue.common.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class CourseQueryDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "课程名称")
    private String courseName;
}
