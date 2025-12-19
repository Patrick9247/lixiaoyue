package com.lixiaoyue.controller;


import com.lixiaoyue.common.BusinessResponse;
import com.lixiaoyue.common.PageVO;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.model.dto.CourseQueryDTO;
import com.lixiaoyue.model.dto.MySchoolClassQueryDTO;
import com.lixiaoyue.model.dto.SchoolClassQueryDTO;
import com.lixiaoyue.model.dto.SchoolClassStudentQueryDTO;
import com.lixiaoyue.model.vo.SchoolClassVO;
import com.lixiaoyue.model.vo.UserStudentVO;
import com.lixiaoyue.model.vo.UserVO;
import com.lixiaoyue.service.ISchoolClassService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "班级管理")
@RestController
@RequestMapping("/schoolClass")
@Slf4j
public class SchoolClassController {

    @Autowired
    ISchoolClassService schoolClassService;

    @PostMapping("/create")
    @Operation(summary = "创建班级")
    public BusinessResponse<SchoolClassVO> create(@RequestBody SchoolClassVO schoolClassVO) {
        SchoolClassVO created = schoolClassService.create(schoolClassVO);
        if (created == null) {
            throw new BusinessException("班级创建失败！");
        }
        return BusinessResponse.success(created);
    }

    @PostMapping("/join")
    @Operation(summary = "加入班级")
    public BusinessResponse<Boolean> join(@Param("schoolClassId")@Schema(description = "班级的id") Long schoolClassId,
                                              @Param("userId")@Schema(description = "学生的用户id") Long userId) {
        Boolean join = schoolClassService.join(schoolClassId, userId);
        if (!join) {
            log.error("加入班级失败，传入userId:{},传入班级Id:{}", userId, schoolClassId);
            throw new BusinessException("加入班级失败！");
        }
        return BusinessResponse.success(true);
    }

    @GetMapping("/list")
    @Operation(summary = "分页查询")
    public BusinessResponse<PageVO<SchoolClassVO>> list(SchoolClassQueryDTO queryDTO) {
        PageVO<SchoolClassVO> page = schoolClassService.pageList(queryDTO);
        return BusinessResponse.success(page);
    }

    @GetMapping("/myClassList")
    @Operation(summary = "我的班级列表，分页查询")
    public BusinessResponse<PageVO<SchoolClassVO>> myList(MySchoolClassQueryDTO queryDTO) {
        PageVO<SchoolClassVO> page = schoolClassService.pageMyList(queryDTO);
        return BusinessResponse.success(page);
    }


    @GetMapping("/studentList")
    @Operation(summary = "查询本班级有哪些学生")
    public BusinessResponse<PageVO<UserStudentVO>> studentList(SchoolClassStudentQueryDTO queryDTO) {
        PageVO<UserStudentVO> page = schoolClassService.pageStudentList(queryDTO);
        return BusinessResponse.success(page);
    }


}
