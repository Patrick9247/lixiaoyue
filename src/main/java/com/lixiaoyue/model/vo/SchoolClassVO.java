package com.lixiaoyue.model.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.tangzc.mpe.autotable.annotation.ColumnComment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class SchoolClassVO implements Serializable {

    @Schema(description = "班级id")
    private Long id;
    @Schema(description = "班级名")
    private String schoolClassName;
    @Schema(description = "课程名")
    private String courseName;
    @Schema(description = "所属课程的id")
    @NotNull(message = "所属课程id不能为空")
    private Long courseId;
    @Schema(description = "负责人Id(教师角色)")
    private Long dutyUserId ;
    @Schema(description = "负责老师名字(教师角色)")
    private String dutyUserName ;
    @Schema(description = "已加入的学生人数")
    private Integer stuCount;
    @Schema(description = "限制的学人数")
    private Integer stuNum;
    @Schema(description = "开设状态:-开设中，-已关闭")
    private int status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreateTime;
}
