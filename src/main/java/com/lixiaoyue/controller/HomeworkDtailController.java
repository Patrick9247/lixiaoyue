package com.lixiaoyue.controller;


import com.lixiaoyue.common.BusinessResponse;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.model.vo.HomeworkDetailVO;
import com.lixiaoyue.model.vo.HomeworkVO;
import com.lixiaoyue.service.IHomeworkDetailService;
import com.lixiaoyue.service.IHomeworkService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(tags = "学生端作业管理")
@RestController
@RequestMapping("/homework")
public class HomeworkDtailController {

    @Autowired
    IHomeworkDetailService homeworkDetailService;

    @PostMapping("/doHomework")
    @Operation(summary = "完成作业")
    public BusinessResponse<HomeworkDetailVO> doHomework(@RequestBody HomeworkDetailVO homeworkDetailVO) {
        HomeworkDetailVO finish = homeworkDetailService.finish(homeworkDetailVO);
        if (finish == null) {
        throw new BusinessException("发布失败，请重试！");
        }
        return BusinessResponse.success(finish);
    }

    @GetMapping("/detail")
    @Operation(summary = "查看作业详情")
    public BusinessResponse<HomeworkDetailVO> detail(@Param("homeworkId")Long homeworkId,@Param("userId") Long userId) {
        HomeworkDetailVO homeworkDetail = homeworkDetailService.getHomeworkDetail(homeworkId, userId);
        return BusinessResponse.success(homeworkDetail);
    }
}
