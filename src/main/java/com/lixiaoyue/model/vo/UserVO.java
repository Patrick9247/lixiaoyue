package com.lixiaoyue.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "年龄")
    private Integer age;
}
