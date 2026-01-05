package com.lixiaoyue.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApplicationVO implements Serializable {

    @Schema(description = "主键")
    private Long id;
    @Schema(description = "申请加入的班级Id")
    private Long schoolClassId;
    @Schema(description = "申请加入的班级名称")
    private String schoolClassName ;
    @Schema(description = "申请加入的课程名称")
    private String courseName;
    @Schema(description = "申请人Id(学生Id)")
    private Long applicatorId;
    @Schema(description = "申请人姓名")
    private String applicatorName;
    @Schema(description = "负责人Id(教师角色)")
    private Long dutyUserId ;
    @Schema(description = "申请状态：未读/未通过/已通过")
    private Integer status;
    @Schema(description = "未通过理由")
    private String remark;
}
