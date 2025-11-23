package com.lixiaoyue.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.lixiaoyue.model.dto.UserPageDTO;
import com.lixiaoyue.model.entity.User;
import com.lixiaoyue.model.vo.UserVO;

public interface IUserService extends IService<User> {

    Page<UserVO> pageUserVO(UserPageDTO userPageDTO);
}
