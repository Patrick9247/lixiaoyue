package com.lixiaoyue.model.dto;

import com.tangzc.mpe.autotable.annotation.ColumnComment;
import lombok.Data;


@Data
public class UserDTO {
    private String username;      // 用户名（唯一）
    private String password;      // 密码（实际项目需加密）
    private String nickname;      // 昵称
    private Integer age;
}
