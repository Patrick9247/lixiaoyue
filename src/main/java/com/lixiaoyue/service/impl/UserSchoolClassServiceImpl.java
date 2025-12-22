package com.lixiaoyue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.mapper.UserSchoolClassMapper;
import com.lixiaoyue.model.entity.UserSchoolClass;
import com.lixiaoyue.service.IUserSchoolClassService;
import org.springframework.stereotype.Service;

@Service
public class UserSchoolClassServiceImpl extends ServiceImpl<UserSchoolClassMapper, UserSchoolClass> implements IUserSchoolClassService {


    @Override
    public Integer countStudentBySchoolClassId(Long schoolClassId) {

        return (int) this.count(new LambdaQueryWrapper<UserSchoolClass>().eq(UserSchoolClass::getSchoolClassId, schoolClassId));
    }
}
