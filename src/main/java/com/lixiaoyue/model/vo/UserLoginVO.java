package com.lixiaoyue.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserLoginVO implements Serializable {
    @Schema(description = "用户名")
    @NotNull(message = "用户名不能为空")
    private String username;
    @Schema(description = "密码")
    @NotNull(message = "密码不能为空")
    @Length(max = 12,message = "密码最大为12位")
    private String password;
    @Schema(description ="角色名")
    @NotNull(message = "用户角色不能为空")
    private String roleName;
}
