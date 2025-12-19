package com.lixiaoyue.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApplicationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "传申请列表查出来的申请id")
    private Long id;
    private Boolean isPass;
    @Schema(description = "未通过理由")
    private String remark;
}
