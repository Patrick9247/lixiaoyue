package com.lixiaoyue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.common.PageVO;
import com.lixiaoyue.enums.HomeworkStatusEnum;
import com.lixiaoyue.exception.BusinessException;

import com.lixiaoyue.mapper.HomeworkMapper;
import com.lixiaoyue.model.dto.HomeworkQueryDTO;
import com.lixiaoyue.model.entity.Application;
import com.lixiaoyue.model.entity.Homework;
import com.lixiaoyue.model.entity.HomeworkDetail;
import com.lixiaoyue.model.vo.HomeworkDetailVO;
import com.lixiaoyue.model.vo.HomeworkFinishCheckVO;
import com.lixiaoyue.model.vo.HomeworkListVO;
import com.lixiaoyue.model.vo.HomeworkVO;
import com.lixiaoyue.service.IApplicationService;
import com.lixiaoyue.service.IHomeworkDetailService;
import com.lixiaoyue.service.IHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework> implements IHomeworkService {

    @Autowired
    private IHomeworkDetailService homeworkDetailService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HomeworkVO publish(HomeworkVO homeworkVO) {
        Homework homework = new Homework();
        BeanUtil.copyProperties(homeworkVO, homework);
        boolean save = this.save(homework);
        if (save) {
            Boolean b = homeworkDetailService.create(homework);
            if (b){
                return homeworkVO;
            }else {
                throw new BusinessException("创建作业记录失败！");
            }

        }

        return null;
    }

    @Override
    public PageVO<HomeworkListVO> pageList(HomeworkQueryDTO queryDTO) {
        Page<Homework> page = new Page<>(queryDTO.getPageNumber(), queryDTO.getPageSize());
        LambdaQueryWrapper<Homework> homeworkLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(queryDTO.getCourseName())){
            homeworkLambdaQueryWrapper.like(Homework::getCourseName, queryDTO.getCourseName());
        }
        if (ObjectUtils.isNotEmpty(queryDTO.getTitle())){
            homeworkLambdaQueryWrapper.like(Homework::getTitle, queryDTO.getTitle());
        }
        if (ObjectUtils.isNotEmpty(queryDTO.getSchoolClassName())){
            homeworkLambdaQueryWrapper.like(Homework::getSchoolClassName, queryDTO.getSchoolClassName());
        }
        if (ObjectUtils.isNotEmpty(queryDTO.getCreatorName())){
            homeworkLambdaQueryWrapper.like(Homework::getCreatorName, queryDTO.getCreatorName());
        }
        if (ObjectUtils.isNotEmpty(queryDTO.getUserId())){
            homeworkLambdaQueryWrapper.eq(Homework::getCreatorId, queryDTO.getUserId());
        }

        Page<Homework> homeworkPage = this.page(page, homeworkLambdaQueryWrapper);
        PageVO<HomeworkListVO> homeworkListVOPageVO = new PageVO<>();
        BeanUtil.copyProperties(homeworkPage, homeworkListVOPageVO);
        List<Homework> records = homeworkPage.getRecords();
        List<HomeworkListVO> homeworkListVOS = BeanUtil.copyToList(records, HomeworkListVO.class);


        countFinish(homeworkListVOS);
        homeworkListVOPageVO.setRecords(homeworkListVOS);
        return homeworkListVOPageVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HomeworkDetailVO finishCheck(HomeworkFinishCheckVO checkVO) {
        HomeworkDetailVO homeworkDetail = homeworkDetailService.getHomeworkDetail(checkVO.getHomeworkId(), checkVO.getOwnerId());
        if (homeworkDetail == null) {
            return null;
        }
        HomeworkDetail save = new HomeworkDetail();
        save.setId(homeworkDetail.getId());
        save.setGmtCheck(new Date());
        save.setGrades(checkVO.getGrades());
        homeworkDetail.setGrades(save.getGrades());
        homeworkDetail.setGmtCheck(save.getGmtCheck());
        homeworkDetailService.updateById(save);
        return homeworkDetail;
    }

    private void countFinish(List<HomeworkListVO> homeworkListVOS) {
        for(HomeworkListVO homeworkListVO:homeworkListVOS){
            List<HomeworkDetail> homeworkDetails = homeworkDetailService.list(
                    new LambdaQueryWrapper<HomeworkDetail>().eq(HomeworkDetail::getHomeworkId, homeworkListVO.getId()));
            long total = homeworkDetails.stream().count();
            long finish = homeworkDetails
                    .stream()
                    .filter(homeworkDetail -> homeworkDetail.getStatus().equals(HomeworkStatusEnum.SUBMITTED.getCode()))
                    .count();
            homeworkListVO.setPublishCount((int)total);
            homeworkListVO.setSubmitCount((int)finish);
        }

        //查本每个homeworkId在homeworkDetail中有多少条

    }
}
