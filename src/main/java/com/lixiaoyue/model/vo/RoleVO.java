package com.lixiaoyue.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class RoleVO {

    @Schema(description = "id")
    private Long Id;
    @Schema(description = "角色名")
    private String roleName;
    @Schema(description = "角色描述")
    private String roleDesc;
}
