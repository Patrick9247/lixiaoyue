package com.lixiaoyue.model.dto;

import com.lixiaoyue.common.BasePage;
import lombok.Data;


@Data
public class UserPageDTO extends BasePage {
    private String username;      // 用户名（唯一）
    private String nickname;      // 昵称
    private Long roleId;
}
