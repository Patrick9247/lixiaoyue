package com.lixiaoyue.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable {
    @Schema(description = "用户id")
    private Long id;
    private String username;      // 用户名（唯一）
    private String nickname;      // 昵称
    private String roleName;
}
