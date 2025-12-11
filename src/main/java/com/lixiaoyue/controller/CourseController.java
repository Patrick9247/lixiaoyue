package com.lixiaoyue.controller;


import com.lixiaoyue.common.BusinessResponse;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.model.vo.CourseVO;
import com.lixiaoyue.service.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "课程管理")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    ICourseService courseService;

    @PostMapping("/create")
    @Operation(summary = "创建课程")
    public BusinessResponse<CourseVO> create(@RequestBody CourseVO courseVO) {
        CourseVO created = courseService.create(courseVO);
        if (created == null) {
            throw new BusinessException("课程创建失败！");
        }
        return BusinessResponse.success(created);
    }
    }
