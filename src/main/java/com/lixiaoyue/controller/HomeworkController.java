package com.lixiaoyue.controller;


import com.lixiaoyue.common.BusinessResponse;
import com.lixiaoyue.common.PageVO;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.model.dto.HomeworkQueryDTO;
import com.lixiaoyue.model.vo.HomeworkDetailVO;
import com.lixiaoyue.model.vo.HomeworkFinishCheckVO;
import com.lixiaoyue.model.vo.HomeworkListVO;
import com.lixiaoyue.model.vo.HomeworkVO;
import com.lixiaoyue.service.IHomeworkService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/list")
    @Operation(summary = "作业分页查询")
    public BusinessResponse<PageVO<HomeworkListVO>> list(HomeworkQueryDTO queryDTO) {
        PageVO<HomeworkListVO> homeworkListVOPageVO = homeworkService.pageList(queryDTO);
        if (homeworkListVOPageVO == null) {
            throw new BusinessException("发布失败，请重试！");
        }
        return BusinessResponse.success(homeworkListVOPageVO);
    }

    @PostMapping("/finishCheck")
    @Operation(summary = "完成批改作业")
    public BusinessResponse<HomeworkDetailVO> finishCheck(@RequestBody HomeworkFinishCheckVO checkVO) {
        HomeworkDetailVO detailVO = homeworkService.finishCheck(checkVO);
        if (detailVO == null) {
            throw new BusinessException("批改失败，请重试！");
        }
        return BusinessResponse.success(detailVO);
    }
}
