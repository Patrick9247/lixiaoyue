package com.lixiaoyue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.enums.HomeworkStatusEnum;
import com.lixiaoyue.mapper.HomeworkDetailMapper;
import com.lixiaoyue.mapper.HomeworkMapper;
import com.lixiaoyue.model.entity.Homework;
import com.lixiaoyue.model.entity.HomeworkDetail;
import com.lixiaoyue.model.vo.HomeworkDetailVO;
import com.lixiaoyue.model.vo.HomeworkVO;
import com.lixiaoyue.model.vo.UserVO;
import com.lixiaoyue.service.IHomeworkDetailService;
import com.lixiaoyue.service.IHomeworkService;
import com.lixiaoyue.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class HomeworkDetailServiceImpl extends ServiceImpl<HomeworkDetailMapper, HomeworkDetail> implements IHomeworkDetailService {

    @Autowired
    private IUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean create(Homework homework) {
        //查出所有学生id
        List<UserVO> userVOS = userService.listBySchoolClassId(homework.getSchoolClassId());
        if (userVOS.isEmpty()){
            return true;
        }
        List<HomeworkDetail> homeworkDetailList = userVOS.stream().map(userVO -> {
            HomeworkDetail homeworkDetail = new HomeworkDetail();
            homeworkDetail.setCreatorId(homework.getCreatorId());
            homeworkDetail.setHomeworkId(homework.getId());
            homeworkDetail.setHomeworkTitle(homework.getTitle());
            homeworkDetail.setOwnerId(userVO.getId());
            homeworkDetail.setStatus(HomeworkStatusEnum.UNSUBMITTED.getCode());
            return homeworkDetail;
        }).collect(Collectors.toList());

        return this.saveBatch(homeworkDetailList);
    }

    @Override
    public HomeworkDetailVO finish(HomeworkDetailVO homeworkDetailVO) {
        HomeworkDetail homeworkDetail = this.getById(homeworkDetailVO.getId());
        if (ObjectUtils.isEmpty(homeworkDetailVO)){
            return null;
        }
        if (homeworkDetail.getStatus().equals(HomeworkStatusEnum.SUBMITTED.getCode())){
            return homeworkDetailVO;
        }
        HomeworkDetail finish  = new HomeworkDetail();
        finish.setId(homeworkDetailVO.getId());
        finish.setStatus(HomeworkStatusEnum.SUBMITTED.getCode());
        finish.setFile(homeworkDetailVO.getFile());
        finish.setGmtSubmit(new Date());
        this.updateById(finish);
        return BeanUtil.copyProperties(finish,HomeworkDetailVO.class);
    }

    @Override
    public HomeworkDetailVO getHomeworkDetail(Long homeworkId, Long userId) {
        HomeworkDetail homeworkDetail = this.getOne(new LambdaQueryWrapper<HomeworkDetail>().eq(HomeworkDetail::getHomeworkId, homeworkId)
                .eq(HomeworkDetail::getOwnerId, userId));
        if (ObjectUtils.isEmpty(homeworkDetail)){
            return null;
        }
        return BeanUtil.copyProperties(homeworkDetail,HomeworkDetailVO.class);
    }
}
