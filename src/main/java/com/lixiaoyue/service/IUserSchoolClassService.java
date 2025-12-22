package com.lixiaoyue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lixiaoyue.model.entity.UserSchoolClass;

public interface IUserSchoolClassService extends IService<UserSchoolClass> {

    Integer countStudentBySchoolClassId(Long schoolClassId);
}
