package com.lixiaoyue.model.dto;


import com.lixiaoyue.common.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class HomeworkStudentQueryDTO extends PageDTO implements Serializable {

    @Schema(description = "学生姓名")
    private String name;
    @Schema(description = "学号")
    private String stuID;
    @Schema(description = "作业id")
    private Long homeworkId;
    @Schema(description = "作业完成状态")
    private Integer status;
}
