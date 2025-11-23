package com.lixiaoyue.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lixiaoyue.common.BasePage;
import com.lixiaoyue.common.BusinessResponse;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.mapper.UserMapper;
import com.lixiaoyue.model.dto.UserPageDTO;
import com.lixiaoyue.model.entity.User;
import com.lixiaoyue.model.vo.UserUpdateVO;
import com.lixiaoyue.model.vo.UserVO;
import com.lixiaoyue.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private UserMapper userMapper;


    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询用户", description = "传入用户ID，返回用户详情（隐藏密码）")
    public BusinessResponse<ResponseEntity<User>> getUserById(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id) {
        User user = userService.getById(id);
        if (ObjectUtils.isEmpty(user)){
            throw new BusinessException("用户ID不存在！");
        }
        return BusinessResponse.success(ResponseEntity.ok(user));
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询所有用户")
    public BusinessResponse<ResponseEntity<Page<UserVO>>> page(UserPageDTO page) {
        Page<User> userPage = new Page<User>();
        userPage.setCurrent(page.getPageNo());
        userPage.setSize(page.getPageSize());
        Page<User> userPages = userService.page(userPage,
                new LambdaQueryWrapper<User>().eq(User::getRoleId,page.getRoleId()));
        List<User> records = userPages.getRecords();
        if (records.isEmpty()){
            throw new BusinessException("用户ID不存在！");
        }
        List<UserVO> userVOS = BeanUtil.copyToList(records, UserVO.class);
        Page<UserVO> userVOPage = new Page<UserVO>();
        userVOPage.setRecords(userVOS);
        userVOPage.setTotal(userPages.getTotal());
        userVOPage.setPages(userPages.getPages());
        userVOPage.setCurrent(userPages.getCurrent());
        return BusinessResponse.success(ResponseEntity.ok(userVOPage));
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

    @PostMapping("/update")
    @Operation(summary = "更新用户信息")
    public BusinessResponse<ResponseEntity<UserUpdateVO>> addUser(@RequestBody UserUpdateVO userUpdateVO) {
        User user = userService.getById(userUpdateVO.getId());
        if (ObjectUtils.isEmpty(user)){
            throw new BusinessException("用户ID不存在！");
        }
        BeanUtil.copyProperties(userUpdateVO,user);
        userService.updateById(user);
        return BusinessResponse.success(ResponseEntity.ok(userUpdateVO));
    }


}
