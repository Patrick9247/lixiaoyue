package com.lixiaoyue.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.lixiaoyue.model.dto.UserLoginDTO;
import com.lixiaoyue.model.dto.UserPageDTO;
import com.lixiaoyue.model.entity.User;
import com.lixiaoyue.model.vo.UserLoginVO;
import com.lixiaoyue.model.vo.UserVO;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface IUserService extends IService<User> {

    Page<UserVO> pageUserVO(UserPageDTO userPageDTO);

    List<Long> listBySchoolClassId(Long schoolClassId);

    UserLoginDTO login(UserLoginVO loginVO);

    Boolean checkExist(@NotNull(message = "用户名不能为空") String username);
}
