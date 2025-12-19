package com.lixiaoyue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.common.PageVO;
import com.lixiaoyue.enums.ApplicationStatusEnum;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.mapper.ApplicationMapper;
import com.lixiaoyue.model.dto.ApplicationDTO;
import com.lixiaoyue.model.dto.ApplicationQueryDTO;
import com.lixiaoyue.model.entity.Application;
import com.lixiaoyue.model.entity.SchoolClass;
import com.lixiaoyue.model.entity.User;
import com.lixiaoyue.model.vo.ApplicationVO;
import com.lixiaoyue.service.IApplicationService;
import com.lixiaoyue.service.ISchoolClassService;
import com.lixiaoyue.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements IApplicationService {

    @Autowired
    ISchoolClassService schoolClassService;
    @Autowired
    IUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean handle(ApplicationDTO applicationDTO) {
        Application application = this.getById(applicationDTO.getId());
        if (application == null) {
            throw new BusinessException("当前申请不存在！");
        }
        Application application1 = new Application();
        application1.setId(applicationDTO.getId());
        if (applicationDTO.getIsPass()){
            application1.setStatus(ApplicationStatusEnum.PASS.getCode());
        }else {
            application1.setStatus(ApplicationStatusEnum.FAIL_PASS.getCode());
            application1.setRemark(applicationDTO.getRemark());
        }
        boolean b = this.updateById(application1);
        //绑定学生id至学生-班级表
        if (b){

        }
        return true;
    }

    @Override
    public PageVO<ApplicationVO> pageList(ApplicationQueryDTO queryDTO) {
        Page<Application> applicationPage = new Page<>(queryDTO.getPageNumber(), queryDTO.getPageSize());
        LambdaQueryWrapper<Application> applicationLambdaQueryWrapper = new LambdaQueryWrapper<>();

        //查询条件
        if (queryDTO.getStatus() != null) {
            applicationLambdaQueryWrapper.eq(Application::getStatus, queryDTO.getStatus());
        }
        if (queryDTO.getCourseName() != null) {
            applicationLambdaQueryWrapper.like(Application::getCourseName, queryDTO.getCourseName());
        }
        if (queryDTO.getSchoolClassName() != null) {
            applicationLambdaQueryWrapper.like(Application::getSchoolClassName, queryDTO.getSchoolClassName());
        }
        if (queryDTO.getApplicatorName() != null) {
            applicationLambdaQueryWrapper.like(Application::getApplicatorName, queryDTO.getApplicatorName());
        }
        if (queryDTO.getApplicatorId() != null) {
            applicationLambdaQueryWrapper.eq(Application::getApplicatorId, queryDTO.getApplicatorId());
        }
        if (queryDTO.getDutyUserId() != null) {
            applicationLambdaQueryWrapper.eq(Application::getDutyUserId, queryDTO.getDutyUserId());
        }

        Page<Application> page = this.page(applicationPage, applicationLambdaQueryWrapper);

        List<Application> records = page.getRecords();
        List<ApplicationVO> applicationVOS = BeanUtil.copyToList(records, ApplicationVO.class);
        PageVO<ApplicationVO> applicationVOPageVO = new PageVO<>();
        BeanUtil.copyProperties(page, applicationVOPageVO);

        applicationVOPageVO.setRecords(applicationVOS);
        return applicationVOPageVO;
    }

    @Override
    public Boolean apply(Long userId, Long schoolClassId) {
        SchoolClass schoolClass = schoolClassService.getById(schoolClassId);
        User user = userService.getById(userId);
        if (ObjectUtils.isEmpty(user)){
            return false;
        }
        if (ObjectUtils.isEmpty(schoolClass)){
            return false;
        }
        Application application = new Application();
        application.setDutyUserId(schoolClass.getDutyUserId());
        application.setApplicatorId(userId);
        application.setStatus(ApplicationStatusEnum.PENDING.getCode());
        application.setSchoolClassId(schoolClassId);
        application.setApplicatorName(user.getUsername());
        application.setCourseName(schoolClass.getCourseName());
        application.setSchoolClassName(schoolClass.getSchoolClassName());
        this.save(application);
        return true;
    }
}
