package com.lixiaoyue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lixiaoyue.model.dto.UserDTO;
import com.lixiaoyue.model.entity.User;

public interface UserService extends IService<User> {
    void addUser(UserDTO userDTO);
}
