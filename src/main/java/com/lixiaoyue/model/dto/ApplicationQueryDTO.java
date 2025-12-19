package com.lixiaoyue.model.dto;

import com.lixiaoyue.common.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApplicationQueryDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "申请加入的班级名称")
    private String schoolClassName ;
    @Schema(description = "申请加入的课程名称")
    private String courseName;
    @Schema(description = "申请人姓名")
    private String applicatorName;
    @Schema(description = "申请人ID")
    private Long applicatorId;
    @Schema(description = "被申请人ID")
    private Long dutyUserId;
    @Schema(description = "申请状态：未读/未通过/已通过")
    private Integer status;
}
