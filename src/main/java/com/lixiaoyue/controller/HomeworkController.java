package com.lixiaoyue.controller;


import com.lixiaoyue.common.BusinessResponse;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.model.vo.HomeworkVO;
import com.lixiaoyue.service.IHomeworkService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "教师端作业管理")
@RestController
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    IHomeworkService homeworkService;

    @PostMapping("/publish")
    @Operation(summary = "发布作业")
    public BusinessResponse<HomeworkVO> publish(@RequestBody HomeworkVO homeworkVO) {
        HomeworkVO published = homeworkService.publish(homeworkVO);
        if (published == null) {
        throw new BusinessException("发布失败，请重试！");
        }
        return BusinessResponse.success(published);
    }
}
