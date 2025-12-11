package com.lixiaoyue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.mapper.CourseMapper;
import com.lixiaoyue.mapper.UserMapper;
import com.lixiaoyue.model.dto.UserPageDTO;
import com.lixiaoyue.model.entity.Course;
import com.lixiaoyue.model.entity.User;
import com.lixiaoyue.model.vo.CourseVO;
import com.lixiaoyue.model.vo.UserVO;
import com.lixiaoyue.service.ICourseService;
import com.lixiaoyue.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
