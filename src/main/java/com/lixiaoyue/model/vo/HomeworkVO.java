package com.lixiaoyue.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class HomeworkVO implements Serializable {

    @Schema(description = "题目")
    private String title;
    @Schema(description = "内容")
    private String content;
    @Schema(description = "创建者id")
    private Long creatorId;
    @Schema(description = "创建者名")
    private String creatorName;
    @Schema(description = "所属课程")
    private String courseName;
    @Schema(description = "所属课程id")
    private Long courseId;
    @Schema(description = "所发布班级id")
    private Long schoolClassId;
}
