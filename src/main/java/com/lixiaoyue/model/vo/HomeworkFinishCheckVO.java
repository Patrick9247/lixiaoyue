package com.lixiaoyue.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class HomeworkFinishCheckVO implements Serializable {

    @Schema(description = "作业id")
    private Long homeworkId;
    @Schema(description = "作业所属学生id")
    private Long ownerId;
    private String remark;
    @Schema(description = "成绩")
    private String grades;
}
