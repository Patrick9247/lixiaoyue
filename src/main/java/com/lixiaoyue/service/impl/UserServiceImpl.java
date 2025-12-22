package com.lixiaoyue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.enums.UserRoleEnum;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.mapper.UserMapper;
import com.lixiaoyue.model.dto.UserLoginDTO;
import com.lixiaoyue.model.dto.UserPageDTO;
import com.lixiaoyue.model.entity.User;
import com.lixiaoyue.model.vo.UserLoginVO;
import com.lixiaoyue.model.vo.UserVO;
import com.lixiaoyue.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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

    @Override
    public UserLoginDTO login(UserLoginVO loginVO) {
        String username = loginVO.getUsername();
        User one = this.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (ObjectUtils.isEmpty(one)){
            throw new BusinessException("用户不存在！");
        }
        String md5Password = DigestUtils.md5DigestAsHex(loginVO.getPassword().getBytes());
        if (!md5Password.equals(one.getPassword())){
            throw new BusinessException("密码错误！");
        }
        String roleName = loginVO.getRoleName();
        if (!roleName.equals(one.getRoleName()) && !roleName.equals(UserRoleEnum.TEACHER.getName())){
            throw new BusinessException("该账号角色不能登录教师端!");
        }

        return BeanUtil.copyProperties(one, UserLoginDTO.class);
    }

    @Override
    public Boolean checkExist(String username) {
        User user = this.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        return !ObjectUtils.isEmpty(user);
    }

    private Wrapper<User> getWrapper(UserPageDTO userPageDTO) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        return null;
    }
}
