package com.lixiaoyue.model.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class HomeworkDetailVO implements Serializable {

    @Schema(description = "作业id")
    private Long id;
    @Schema(description = "文件地址")
    private String file;
    @Schema(description = "作业名称")
    private String homeworkTitle;
    @Schema(description = "作业所属学生id")
    private String ownerId;
    @Schema(description = "成绩")
    private String grades;
    @Schema(description = "教师评语")
    private String remark;
    @Schema(description = "文件大小")
    private String size;
    @Schema(description = "提交设备")
    private String device;
    @Schema(description = "状态")
    private Integer status;
    @Schema(description = "提交时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtSubmit;
    @Schema(description = "批改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCheck;

}
