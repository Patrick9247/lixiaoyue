package com.lixiaoyue.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TextDTO {

    @Schema(description = "标题")
    private String title;
    @Schema(description = "内容")
    private String content ;
    @Schema(description = "所属人id")
    private Long userId;
    @Schema(description = "所属作业id")
    private Long homeworkId;
}
