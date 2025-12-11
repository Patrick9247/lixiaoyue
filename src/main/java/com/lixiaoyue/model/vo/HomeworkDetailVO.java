package com.lixiaoyue.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class HomeworkDetailVO implements Serializable {

    @Schema(description = "作业id")
    private Long id;
    @Schema(description = "文件地址")
    private String file;
    private String name;
    private String userId;
}
