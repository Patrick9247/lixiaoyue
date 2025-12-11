package com.lixiaoyue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.mapper.UserMapper;
import com.lixiaoyue.model.dto.UserPageDTO;
import com.lixiaoyue.model.entity.User;
import com.lixiaoyue.model.vo.UserVO;
import com.lixiaoyue.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Page<UserVO> pageUserVO(UserPageDTO userPageDTO) {
        Page<User> page = new Page<>();
        page.setCurrent(userPageDTO.getPageNo());
        page.setSize(userPageDTO.getPageSize());
        Wrapper<User> wrapper = getWrapper(userPageDTO);
        Page<User> userPage = userMapper.selectPage(page, wrapper);

        List<User> records = userPage.getRecords();
        List<UserVO> userVOS = BeanUtil.copyToList(records, UserVO.class);
        Page<UserVO> userVOPage = new Page<>();
        userVOPage.setRecords(userVOS);
        userVOPage.setTotal(userPage.getTotal());
        userVOPage.setCurrent(userPage.getCurrent());
        userVOPage.setSize(userPage.getSize());
        userVOPage.setPages(userPage.getPages());
        return userVOPage;
    }

    @Override
    public List<UserVO> listBySchoolClassId(Long schoolClassId) {
        List<User> users = userMapper.selectList(new QueryWrapper<User>().eq("school_class_id", schoolClassId));
        if (CollectionUtil.isEmpty(users)){
            return Collections.emptyList();
        }
        return BeanUtil.copyToList(users, UserVO.class);
    }

    private Wrapper<User> getWrapper(UserPageDTO userPageDTO) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        return null;
    }
}
