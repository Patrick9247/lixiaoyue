package com.lixiaoyue.controller;


import com.lixiaoyue.common.BusinessResponse;
import com.lixiaoyue.common.PageVO;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.model.dto.ApplicationDTO;
import com.lixiaoyue.model.dto.ApplicationQueryDTO;
import com.lixiaoyue.model.vo.ApplicationVO;
import com.lixiaoyue.service.IApplicationService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "申请管理")
@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    IApplicationService applicationService;

    @PostMapping("/handle")
    @Operation(summary = "处理申请")
    public BusinessResponse<Boolean> handle(@RequestBody ApplicationDTO applicationDTO) {
        Boolean handle = applicationService.handle(applicationDTO);
        return BusinessResponse.success(handle);
    }

    @GetMapping("/list")
    @Operation(summary = "分页查询")
    public BusinessResponse<PageVO<ApplicationVO>> list(ApplicationQueryDTO queryDTO) {
        PageVO<ApplicationVO> page = applicationService.pageList(queryDTO);
        return BusinessResponse.success(page);
    }


    @PostMapping("/apply")
    @Operation(summary = "学生提交申请")
    public BusinessResponse<Boolean> apply(@Param("userId")Long userId, @Param("schoolClassId") Long schoolClassId) {
        Boolean apply = applicationService.apply(userId,schoolClassId);
        if (!apply){
            throw new BusinessException("申请失败，请联系管理员！");
        }
        return BusinessResponse.success(apply);
    }

}
