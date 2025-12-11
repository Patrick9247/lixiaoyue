package com.lixiaoyue.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CourseVO {

    @Schema(description = "课程名")
    private String courseName;
    @Schema(description = "负责人Id(教师角色)")
    private Long dutyUserId ;
    @Schema(description = "课程状态")
    private Integer status;
}
