package com.lixiaoyue.controller;

import cn.hutool.core.bean.BeanUtil;
import com.lixiaoyue.common.R;
import com.lixiaoyue.model.entity.User;
import com.lixiaoyue.model.vo.UserVO;
import com.lixiaoyue.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询用户", description = "传入用户ID，返回用户详情（隐藏密码）")
    public ResponseEntity<User> getUserById(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    @Operation(summary = "增加用户")
    public ResponseEntity<UserVO> addUser(@RequestBody UserVO userVO) {
        User user = new User();
        BeanUtil.copyProperties(userVO,user);
        userService.save(user);
        return ResponseEntity.ok(userVO);
    }

    @PostMapping("/register")
    @Operation(summary = "注册")
    public ResponseEntity<UserVO> register(@RequestBody UserVO userVO) {
        User user = new User();
        BeanUtil.copyProperties(userVO,user);
        String md5Password = DigestUtils.md5DigestAsHex(userVO.getPassword().getBytes());
        user.setPassword(md5Password);
        userService.save(user);
        return ResponseEntity.ok(userVO);
    }

}
