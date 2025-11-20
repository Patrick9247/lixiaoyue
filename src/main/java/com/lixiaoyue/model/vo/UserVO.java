package com.lixiaoyue.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserVO implements Serializable {
    @Schema(description = "用户名")
    @NotNull(message = "用户名不能为空")
    private String username;
    @Schema(description = "密码")
    @Length(max = 12,message = "密码最大为12位")
    private String password;
    @Schema(description = "昵称")
    @NotNull(message = "昵称不能为空")
    private String nickname;
    @Schema(description = "年龄")
    private Integer age;
}
