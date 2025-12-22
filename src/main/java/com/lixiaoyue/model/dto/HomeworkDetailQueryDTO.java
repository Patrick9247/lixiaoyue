package com.lixiaoyue.model.dto;


import com.lixiaoyue.common.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class HomeworkDetailQueryDTO extends PageDTO implements Serializable {

    @Schema(description = "作业题目")
    private String homeworkTitle;
    @Schema(description = "教师id")
    private Long teacherUserId;
    @Schema(description = "学生id")
    @NotNull
    private Long studentUserId;
    @Schema(description = "作业完成状态")
    private Integer status;
}
