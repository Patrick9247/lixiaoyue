package com.lixiaoyue.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lixiaoyue.common.BusinessResponse;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.model.dto.UserLoginDTO;
import com.lixiaoyue.model.dto.UserPageDTO;
import com.lixiaoyue.model.dto.UserRegisterDTO;
import com.lixiaoyue.model.entity.User;
import com.lixiaoyue.model.vo.HomeworkDetailVO;
import com.lixiaoyue.model.vo.UserLoginVO;
import com.lixiaoyue.model.vo.UserUpdateVO;
import com.lixiaoyue.model.vo.UserVO;
import com.lixiaoyue.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询用户", description = "传入用户ID，返回用户详情（隐藏密码）")
    public BusinessResponse<User> getUserById(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id) {
        User user = userService.getById(id);
        if (ObjectUtils.isEmpty(user)){
            throw new BusinessException("用户ID不存在！");
        }
        return BusinessResponse.success(user);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询所有用户")
    public BusinessResponse<Page<UserVO>> page(UserPageDTO page) {
        Page<User> userPage = new Page<User>();
        userPage.setCurrent(page.getPageNo());
        userPage.setSize(page.getPageSize());
        Page<User> userPages = userService.page(userPage,
                new LambdaQueryWrapper<User>().eq(page.getRoleName()!=null,User::getRoleName,page.getRoleName())
                        .like(page.getUsername() != null,User::getUsername,page.getUsername())
                        .like(page.getNickname() != null,User::getNickname,page.getNickname()));
        List<User> records = userPages.getRecords();


        List<UserVO> userVOS = BeanUtil.copyToList(records, UserVO.class);
        Page<UserVO> userVOPage = new Page<UserVO>();
        userVOPage.setRecords(userVOS);
        userVOPage.setTotal(userPages.getTotal());
        userVOPage.setPages(userPages.getPages());
        userVOPage.setCurrent(userPages.getCurrent());
        return BusinessResponse.success(userVOPage);
    }


    @PostMapping("/register")
    @Operation(summary = "注册")
    public BusinessResponse<User> register(@RequestBody UserRegisterDTO userVO) {
        Boolean b = userService.checkExist(userVO.getUsername());
        if (b){
            throw new BusinessException("用户名已存在");
        }
        User user = new User();
        BeanUtil.copyProperties(userVO,user);
        String md5Password = DigestUtils.md5DigestAsHex(userVO.getPassword().getBytes());
        user.setPassword(md5Password);
        userService.save(user);
        return BusinessResponse.success(user);
    }

    @PostMapping("/login")
    @Operation(summary = "登录")
    public BusinessResponse<UserLoginDTO> login(@RequestBody UserLoginVO loginVO) {
        UserLoginDTO login = userService.login(loginVO);
        if (ObjectUtils.isEmpty(login)){
            throw new BusinessException("登录失败，请联系管理员");
        }
        return BusinessResponse.success(login);
    }

    @PostMapping("/update")
    @Operation(summary = "更新用户信息")
    public BusinessResponse<UserUpdateVO> addUser(@RequestBody UserUpdateVO userUpdateVO) {
        User user = userService.getById(userUpdateVO.getId());
        if (ObjectUtils.isEmpty(user)){
            throw new BusinessException("用户ID不存在！");
        }
        BeanUtil.copyProperties(userUpdateVO,user);
        userService.updateById(user);
        return BusinessResponse.success(userUpdateVO);
    }


}
