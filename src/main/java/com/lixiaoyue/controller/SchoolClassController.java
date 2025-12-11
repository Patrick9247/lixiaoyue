package com.lixiaoyue.controller;


import com.lixiaoyue.common.BusinessResponse;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.model.vo.SchoolClassVO;
import com.lixiaoyue.service.ISchoolClassService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public BusinessResponse<Boolean> join(@Param("schoolClassId") Long schoolClassId,
                                              @Param("userId")Long userId) {
        Boolean join = schoolClassService.join(schoolClassId, userId);
        if (!join) {
            log.error("加入班级失败，传入userId:{},传入班级Id:{}", userId, schoolClassId);
            throw new BusinessException("加入班级失败！");
        }
        return BusinessResponse.success(true);
    }



}
