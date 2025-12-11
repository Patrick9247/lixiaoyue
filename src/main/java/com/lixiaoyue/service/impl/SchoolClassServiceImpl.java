package com.lixiaoyue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.mapper.CourseMapper;
import com.lixiaoyue.mapper.SchoolClassMapper;
import com.lixiaoyue.model.entity.Course;
import com.lixiaoyue.model.entity.SchoolClass;
import com.lixiaoyue.model.entity.User;
import com.lixiaoyue.model.vo.SchoolClassVO;
import com.lixiaoyue.service.ICourseService;
import com.lixiaoyue.service.ISchoolClassService;
import com.lixiaoyue.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class SchoolClassServiceImpl extends ServiceImpl<SchoolClassMapper, SchoolClass> implements ISchoolClassService {

    @Autowired
    IUserService userService;

    @Override
    public SchoolClassVO create(SchoolClassVO schoolClassVO) {
        SchoolClass schoolClass = new SchoolClass();
        BeanUtil.copyProperties(schoolClassVO, schoolClass);
        boolean save = this.save(schoolClass);
        if (save){
            return schoolClassVO;
        }
        return null;
    }

    @Override
    public Boolean join(Long schoolClassId, Long userId) {
        User user = userService.getById(userId);
        SchoolClass schoolClass = this.getById(schoolClassId);
        if (ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(schoolClass)){
            return false;
        }
        User update = new User();
        update.setId(userId);
        update.setSchoolClassId(schoolClassId);

        boolean b = userService.updateById(update);
        return b;
    }
}
