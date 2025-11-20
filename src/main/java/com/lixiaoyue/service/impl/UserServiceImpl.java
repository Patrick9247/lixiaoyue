package com.lixiaoyue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.mapper.UserMapper;
import com.lixiaoyue.model.dto.UserDTO;
import com.lixiaoyue.model.entity.User;
import com.lixiaoyue.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;




    @Override
    public void addUser(UserDTO userDTO) {
        User user = new User();
        BeanUtil.copyProperties(userDTO, user);
        userMapper.insert(user);
    }
}
