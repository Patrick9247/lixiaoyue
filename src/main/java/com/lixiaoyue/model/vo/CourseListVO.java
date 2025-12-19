package com.lixiaoyue.model.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.tangzc.mpe.autotable.annotation.ColumnComment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CourseListVO implements Serializable {

    @Schema(description = "课程id")
    private Long id;
    @Schema(description = "班级名")
    private String schoolClassName;
    @Schema(description = "课程名")
    private String courseName;
    @ColumnComment("负责人Id(教师角色)")
    private Long dutyUserId ;
    @ColumnComment("开设状态:-开设中，-已关闭")
    private int status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreateTime;
}
