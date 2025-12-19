package com.lixiaoyue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lixiaoyue.common.PageVO;
import com.lixiaoyue.model.dto.CourseQueryDTO;
import com.lixiaoyue.model.dto.SchoolClassQueryDTO;
import com.lixiaoyue.model.entity.Course;
import com.lixiaoyue.model.entity.Role;
import com.lixiaoyue.model.vo.CourseListVO;
import com.lixiaoyue.model.vo.CourseUpdateVO;
import com.lixiaoyue.model.vo.CourseVO;

public interface ICourseService extends IService<Course> {
    CourseVO create(CourseVO courseVO);

    Boolean delete(Long courseId);

    Boolean updateCourse(CourseUpdateVO courseUpdateVO);

    PageVO<CourseListVO> pageList(CourseQueryDTO queryDTO);
}
