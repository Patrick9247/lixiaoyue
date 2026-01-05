package com.lixiaoyue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.common.PageVO;
import com.lixiaoyue.enums.HomeworkStatusEnum;
import com.lixiaoyue.mapper.HomeworkDetailMapper;
import com.lixiaoyue.model.dto.HomeworkDetailQueryDTO;
import com.lixiaoyue.model.dto.HomeworkStudentQueryDTO;
import com.lixiaoyue.model.dto.StudentHomeworkUserDTO;
import com.lixiaoyue.model.entity.Homework;
import com.lixiaoyue.model.entity.HomeworkDetail;
import com.lixiaoyue.model.entity.User;
import com.lixiaoyue.model.vo.HomeworkDetailVO;
import com.lixiaoyue.service.IHomeworkDetailService;
import com.lixiaoyue.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
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
        List<Long> users = userService.listBySchoolClassId(homework.getSchoolClassId());
        if (users.isEmpty()){
            return true;
        }
        List<HomeworkDetail> homeworkDetailList = users.stream().map(userId -> {
            HomeworkDetail homeworkDetail = new HomeworkDetail();
            homeworkDetail.setCreatorId(homework.getCreatorId());
            homeworkDetail.setHomeworkId(homework.getId());
            homeworkDetail.setHomeworkTitle(homework.getTitle());
            homeworkDetail.setOwnerId(userId);
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
        if (homeworkDetail.getStatus().equals(HomeworkStatusEnum.AI_CORRECT.getCode())){
            return homeworkDetailVO;
        }
        HomeworkDetail finish  = new HomeworkDetail();
        finish.setId(homeworkDetailVO.getId());
        finish.setStatus(HomeworkStatusEnum.AI_CORRECT.getCode());
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

    @Override
    public PageVO<HomeworkDetailVO> getStudentHomeworkPage(HomeworkDetailQueryDTO homeworkDetailPageQueryVO) {
        Page<HomeworkDetail> homeworkDetailPage = new Page<HomeworkDetail>(homeworkDetailPageQueryVO.getPageNumber(), homeworkDetailPageQueryVO.getPageSize());
        LambdaQueryWrapper<HomeworkDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (homeworkDetailPageQueryVO.getHomeworkTitle() != null){
            lambdaQueryWrapper.like(HomeworkDetail::getHomeworkTitle, homeworkDetailPageQueryVO.getHomeworkTitle());
        }
        if (homeworkDetailPageQueryVO.getStatus() != null){
            lambdaQueryWrapper.eq(HomeworkDetail::getStatus, homeworkDetailPageQueryVO.getStatus());
        }
        if (homeworkDetailPageQueryVO.getTeacherUserId() != null){
            lambdaQueryWrapper.eq(HomeworkDetail::getCreatorId, homeworkDetailPageQueryVO.getTeacherUserId());
        }
        lambdaQueryWrapper.eq(HomeworkDetail::getOwnerId,homeworkDetailPageQueryVO.getStudentUserId());

        Page<HomeworkDetail> detailPage = this.page(homeworkDetailPage, lambdaQueryWrapper);
        PageVO<HomeworkDetailVO> homeworkDetailVOPageVO = new PageVO<>();
        BeanUtil.copyProperties(detailPage,homeworkDetailVOPageVO);

        List<HomeworkDetail> records = detailPage.getRecords();
        List<HomeworkDetailVO> homeworkDetailVOS = BeanUtil.copyToList(records, HomeworkDetailVO.class);

        homeworkDetailVOPageVO.setRecords(homeworkDetailVOS);
        return homeworkDetailVOPageVO;
    }

    @Override
    public PageVO<StudentHomeworkUserDTO> getUserPageByHomework(HomeworkStudentQueryDTO homeworkStudentQueryDTO) {
        Page<HomeworkDetail> homeworkDetailPage = new Page<HomeworkDetail>(homeworkStudentQueryDTO.getPageNumber(), homeworkStudentQueryDTO.getPageSize());
        LambdaQueryWrapper<HomeworkDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (homeworkStudentQueryDTO.getHomeworkId() != null){
            lambdaQueryWrapper.eq(HomeworkDetail::getHomeworkId, homeworkStudentQueryDTO.getHomeworkId());
        }
        if (homeworkStudentQueryDTO.getStatus() != null){
            lambdaQueryWrapper.eq(HomeworkDetail::getStatus, homeworkStudentQueryDTO.getStatus());
        }

        Page<HomeworkDetail> detailPage = this.page(homeworkDetailPage, lambdaQueryWrapper);

        List<HomeworkDetail> records = detailPage.getRecords();
        List<StudentHomeworkUserDTO> studentHomeworkUserDTOS = new ArrayList<>();
        records.forEach(homeworkDetail -> {
            Long ownerId = homeworkDetail.getOwnerId();
            User byId = userService.getById(ownerId);
            StudentHomeworkUserDTO studentHomeworkUserDTO = new StudentHomeworkUserDTO();
            studentHomeworkUserDTO.setHomeworkId(homeworkDetail.getHomeworkId());
            studentHomeworkUserDTO.setUserName(byId.getUsername());
            studentHomeworkUserDTO.setUserId(ownerId);
            studentHomeworkUserDTO.setStatus(homeworkDetail.getStatus());
            studentHomeworkUserDTO.setGrades(homeworkDetail.getGrades());
            studentHomeworkUserDTO.setHomeworkTitle(homeworkDetail.getHomeworkTitle());
            studentHomeworkUserDTO.setFile(homeworkDetail.getFile());
            studentHomeworkUserDTO.setDevice(homeworkDetail.getDevice());
            studentHomeworkUserDTO.setGmtCheck(homeworkDetail.getGmtCheck());
            studentHomeworkUserDTO.setGmtSubmit(homeworkDetail.getGmtSubmit());
            studentHomeworkUserDTO.setSize(homeworkDetail.getSize());
            studentHomeworkUserDTO.setRemark(homeworkDetail.getRemark());
            studentHomeworkUserDTOS.add(studentHomeworkUserDTO);
        });

        PageVO<StudentHomeworkUserDTO> studentHomeworkUserDTOPageVO = new PageVO<StudentHomeworkUserDTO>();
        BeanUtil.copyProperties(detailPage,studentHomeworkUserDTOPageVO);
        studentHomeworkUserDTOPageVO.setRecords(studentHomeworkUserDTOS);
        return studentHomeworkUserDTOPageVO;
    }

}
