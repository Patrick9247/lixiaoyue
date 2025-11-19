package com.lixiaoyue.controller;

import com.lixiaoyue.common.R;
import com.lixiaoyue.model.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询用户", description = "传入用户ID，返回用户详情（隐藏密码）")
    public R<UserVO> getUserById(
            @Parameter(description = "用户ID", required = true, example = "1")
            @PathVariable Long id) {
        return new R<>(200,"成功",null);
    }

}
