package com.lixiaoyue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.common.PageVO;
import com.lixiaoyue.mapper.CourseMapper;
import com.lixiaoyue.model.dto.CourseQueryDTO;
import com.lixiaoyue.model.dto.SchoolClassQueryDTO;
import com.lixiaoyue.model.entity.Application;
import com.lixiaoyue.model.entity.Course;
import com.lixiaoyue.model.vo.CourseListVO;
import com.lixiaoyue.model.vo.CourseUpdateVO;
import com.lixiaoyue.model.vo.CourseVO;
import com.lixiaoyue.service.ICourseService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;


@Service
public class CourceServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Override
    public CourseVO create(CourseVO courseVO) {
        Course course = new Course();
        BeanUtil.copyProperties(courseVO,course);
        boolean save = this.save(course);
        if (save) {
            return courseVO;
        }
        return null;
    }

    @Override
    public Boolean delete(Long courseId) {
        return this.removeById(courseId);
    }

    @Override
    public Boolean updateCourse(CourseUpdateVO courseUpdateVO) {
        Long courseId = courseUpdateVO.getId();
        Course course = this.getById(courseId);
        if(ObjectUtils.isEmpty(course)){
            return false;
        }
        Course newCourse = new Course();
        BeanUtil.copyProperties(courseUpdateVO,newCourse);
        return this.updateById(newCourse);
    }

    @Override
    public PageVO<CourseListVO> pageList(CourseQueryDTO queryDTO) {
        Page<Course> coursePage = new Page<>(queryDTO.getPageNumber(), queryDTO.getPageSize());
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (queryDTO.getCourseName() != null) {
            courseLambdaQueryWrapper.like(Course::getCourseName, queryDTO.getCourseName());
        }

        Page<Course> page = this.page(coursePage, courseLambdaQueryWrapper);
        List<Course> records = page.getRecords();

        PageVO<CourseListVO> courseListVOPageVO = new PageVO<CourseListVO>();
        BeanUtil.copyProperties(page, courseListVOPageVO);
        List<CourseListVO> courseListVOS = BeanUtil.copyToList(records, CourseListVO.class);
        courseListVOPageVO.setRecords(courseListVOS);

        return courseListVOPageVO;
    }
}
