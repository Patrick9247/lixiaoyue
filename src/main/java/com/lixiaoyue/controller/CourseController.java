package com.lixiaoyue.controller;


import com.lixiaoyue.common.BusinessResponse;
import com.lixiaoyue.common.PageVO;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.model.dto.CourseQueryDTO;
import com.lixiaoyue.model.dto.SchoolClassQueryDTO;
import com.lixiaoyue.model.vo.CourseListVO;
import com.lixiaoyue.model.vo.CourseUpdateVO;
import com.lixiaoyue.model.vo.CourseVO;
import com.lixiaoyue.model.vo.SchoolClassVO;
import com.lixiaoyue.service.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete")
    @Operation(summary = "删除课程")
    public BusinessResponse<Boolean> delete(@Param("courseId")Long courseId) {
        Boolean delete = courseService.delete(courseId);
        if (!delete) {
            throw new BusinessException("课程删除失败！");
        }
        return BusinessResponse.success(delete);
    }

    @PostMapping("/update")
    @Operation(summary = "修改课程")
    public BusinessResponse<CourseUpdateVO> update(@RequestBody CourseUpdateVO courseUpdateVO) {
        Boolean b = courseService.updateCourse(courseUpdateVO);
        if (!b){
            throw new BusinessException("更新失败！");
        }
        return BusinessResponse.success(courseUpdateVO);
    }

    @GetMapping("/list")
    @Operation(summary = "分页查询课程列表")
    public BusinessResponse<PageVO<CourseListVO>> list(CourseQueryDTO queryDTO) {
        PageVO<CourseListVO> page = courseService.pageList(queryDTO);
        return BusinessResponse.success(page);
    }

    @GetMapping("/teacher/list")
    @Operation(summary = "分页查询该课程下所有课程列表")
    public BusinessResponse<PageVO<CourseListVO>> getTeachers(CourseQueryDTO queryDTO) {
        PageVO<CourseListVO> page = courseService.pageList(queryDTO);
        return BusinessResponse.success(page);
    }



}
