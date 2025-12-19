package com.lixiaoyue.model.dto;


import com.lixiaoyue.common.PageDTO;
import com.lixiaoyue.common.PageVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class HomeworkQueryDTO extends PageDTO implements Serializable {

    @Schema(description = "题目")
    private String title;
    @Schema(description = "创建者名")
    private String creatorName;
    @Schema(description = "所属课程")
    private String courseName;
    @Schema(description = "所发布班级名称")
    private Long schoolClassName;
}
