package com.lixiaoyue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lixiaoyue.model.entity.Course;
import com.lixiaoyue.model.entity.Role;
import com.lixiaoyue.model.vo.CourseVO;

public interface ICourseService extends IService<Course> {
    CourseVO create(CourseVO courseVO);
}
