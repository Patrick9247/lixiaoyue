package com.lixiaoyue.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApplyDTO implements Serializable {
    //todo apply学生
    private static final long serialVersionUID = 1L;
    private Long id;
    private Boolean isPass;
    @Schema(description = "未通过理由")
    private String remark;
}
