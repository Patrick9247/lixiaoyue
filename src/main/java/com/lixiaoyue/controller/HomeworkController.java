package com.lixiaoyue.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lixiaoyue.common.BusinessResponse;
import com.lixiaoyue.common.PageVO;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.model.dto.HomeworkQueryDTO;
import com.lixiaoyue.model.dto.HomeworkStudentQueryDTO;
import com.lixiaoyue.model.dto.StudentHomeworkUserDTO;
import com.lixiaoyue.model.entity.Homework;
import com.lixiaoyue.model.vo.*;
import com.lixiaoyue.service.IHomeworkDetailService;
import com.lixiaoyue.service.IHomeworkService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "教师端作业管理")
@RestController
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    IHomeworkService homeworkService;
    @Autowired
    IHomeworkDetailService homeworkDetailService;

    @PostMapping("/publish")
    @Operation(summary = "发布作业")
    public BusinessResponse<HomeworkVO> publish(@RequestBody HomeworkVO homeworkVO) {
        HomeworkVO published = homeworkService.publish(homeworkVO);
        if (published == null) {
        throw new BusinessException("发布失败，请重试！");
        }
        return BusinessResponse.success(published);
    }

    @PostMapping("/delete/{homeworkId}")
    @Operation(summary = "删除作业")
    public BusinessResponse<Boolean> delete(@PathVariable("homeworkId" ) Long homeworkId) {
        Homework homework = new Homework();
        homework.setId(homeworkId);
        boolean b = homeworkService.removeById(homework);
        if (!b) {
            throw new BusinessException("删除失败！");
        }
        return BusinessResponse.success(b);
    }

    @GetMapping("/studentByHomework/page")
    @Operation(summary = "根据作业id查询学生用户")
    public BusinessResponse<PageVO<StudentHomeworkUserDTO>> studentPage(HomeworkStudentQueryDTO homeworkStudentQueryDTO) {
        PageVO<StudentHomeworkUserDTO> userPageByHomework = homeworkDetailService.getUserPageByHomework(homeworkStudentQueryDTO);
        return BusinessResponse.success(userPageByHomework);
    }

    @GetMapping("{homeworkId}")
    @Operation(summary = "根据作业id查询布置的作业")
    public BusinessResponse<HomeworkTeacherVO> get(@PathVariable("homeworkId") Long homeworkId) {
        Homework byId = homeworkService.getById(homeworkId);
        HomeworkTeacherVO homeworkTeacherVO = BeanUtil.copyProperties(byId, HomeworkTeacherVO.class);
        return BusinessResponse.success(homeworkTeacherVO);
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
