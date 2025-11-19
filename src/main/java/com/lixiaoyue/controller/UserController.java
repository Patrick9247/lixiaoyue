package com.lixiaoyue.controller;

import com.lixiaoyue.common.R;
import com.lixiaoyue.common.convert.ConvertUtil;
import com.lixiaoyue.model.dto.UserDTO;
import com.lixiaoyue.model.entity.User;
import com.lixiaoyue.model.vo.UserVO;
import com.lixiaoyue.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询用户", description = "传入用户ID，返回用户详情（隐藏密码）")
    public R<UserVO> getUserById(
            @Parameter(description = "用户ID", required = true, example = "1")
            @PathVariable Long id) {
        return new R<>(200,"成功",null);
    }

    @PostMapping("/add")
    public Response<Void> addUser(UserVO userVO) {
        User user = ConvertUtil.convert(userVO, User.class);
        userService.save(user);
        return null;
    }

}
