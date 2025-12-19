package com.lixiaoyue.model.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class HomeworkListVO implements Serializable {

    @Schema(description = "id")
    private Long id;
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
    @Schema(description = "发布数")
    private Integer publishCount;
    @Schema(description = "提交数")
    private Integer submitCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "发布时间")
    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "截止时间")
    private Date deadline;
}
