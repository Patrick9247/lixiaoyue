package com.lixiaoyue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.common.PageVO;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.mapper.CourseMapper;
import com.lixiaoyue.mapper.SchoolClassMapper;
import com.lixiaoyue.model.dto.MySchoolClassQueryDTO;
import com.lixiaoyue.model.dto.SchoolClassQueryDTO;
import com.lixiaoyue.model.dto.SchoolClassStudentQueryDTO;
import com.lixiaoyue.model.entity.Course;
import com.lixiaoyue.model.entity.SchoolClass;
import com.lixiaoyue.model.entity.User;
import com.lixiaoyue.model.entity.UserSchoolClass;
import com.lixiaoyue.model.vo.SchoolClassVO;
import com.lixiaoyue.model.vo.UserStudentVO;
import com.lixiaoyue.service.ICourseService;
import com.lixiaoyue.service.ISchoolClassService;
import com.lixiaoyue.service.IUserSchoolClassService;
import com.lixiaoyue.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static net.sf.jsqlparser.parser.feature.Feature.update;


@Service
public class SchoolClassServiceImpl extends ServiceImpl<SchoolClassMapper, SchoolClass> implements ISchoolClassService {

    @Autowired
    IUserService userService;

    @Autowired
    IUserSchoolClassService userSchoolClassService;

    @Override
    public SchoolClassVO create(SchoolClassVO schoolClassVO) {
        SchoolClass schoolClass = new SchoolClass();
        BeanUtil.copyProperties(schoolClassVO, schoolClass);
        boolean save = this.save(schoolClass);
        if (save){
            return schoolClassVO;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean join(Long schoolClassId, Long userId) {
        User user = userService.getById(userId);
        SchoolClass schoolClass = this.getById(schoolClassId);
        if (ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(schoolClass)){
            return false;
        }
        UserSchoolClass userSchoolClass = new UserSchoolClass();
        userSchoolClass.setSchoolClassId(schoolClassId);
        userSchoolClass.setUserId(userId);
        UserSchoolClass userSchoolClass1 = userSchoolClassService.getOne(new LambdaQueryWrapper<UserSchoolClass>()
                .eq(UserSchoolClass::getUserId, userId)
                .eq(UserSchoolClass::getSchoolClassId, schoolClassId));
        if (userSchoolClass1 != null){
            throw new BusinessException("已加入该班级，无法重复加入");
        }
        return userSchoolClassService.save(userSchoolClass);
    }

    @Override
    public PageVO<SchoolClassVO> pageList(SchoolClassQueryDTO queryDTO) {
        Page<SchoolClass> page = new Page<>(queryDTO.getPageNumber(), queryDTO.getPageSize());
        LambdaQueryWrapper<SchoolClass> schoolClassLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(queryDTO.getSchoolClassName())){
            schoolClassLambdaQueryWrapper.like(SchoolClass::getSchoolClassName, queryDTO.getSchoolClassName());
        }
        if (ObjectUtils.isNotEmpty(queryDTO.getCourseName())){
            schoolClassLambdaQueryWrapper.like(SchoolClass::getCourseName, queryDTO.getSchoolClassName());
        }
        if (ObjectUtils.isNotEmpty(queryDTO.getCourseId())){
            schoolClassLambdaQueryWrapper.eq(SchoolClass::getCourseId, queryDTO.getCourseId());
        }

        Page<SchoolClass> schoolClassPage = this.page(page, schoolClassLambdaQueryWrapper);

        List<SchoolClass> records = schoolClassPage.getRecords();

        List<SchoolClassVO> schoolClassVOS = BeanUtil.copyToList(records, SchoolClassVO.class);

        PageVO<SchoolClassVO> pageVO = new PageVO<>();
        BeanUtil.copyProperties(schoolClassPage, pageVO);
        pageVO.setRecords(schoolClassVOS);
        return pageVO;
    }

    @Override
    public PageVO<SchoolClassVO> pageMyList(MySchoolClassQueryDTO queryDTO) {
        Long userId = queryDTO.getId();
        List<UserSchoolClass> userSchoolClasses = userSchoolClassService.list(new LambdaQueryWrapper<UserSchoolClass>().eq(UserSchoolClass::getUserId, userId));
        List<Long> schoolClassIds = userSchoolClasses.stream().map(UserSchoolClass::getSchoolClassId).collect(Collectors.toList());

        Page<SchoolClass> page = new Page<>(queryDTO.getPageNumber(), queryDTO.getPageSize());
        LambdaQueryWrapper<SchoolClass> schoolClassLambdaQueryWrapper = new LambdaQueryWrapper<>();

        if (ObjectUtils.isNotEmpty(queryDTO.getSchoolClassName())){
            schoolClassLambdaQueryWrapper.like(SchoolClass::getSchoolClassName, queryDTO.getSchoolClassName());
        }
        if (ObjectUtils.isNotEmpty(queryDTO.getCourseName())){
            schoolClassLambdaQueryWrapper.like(SchoolClass::getCourseName, queryDTO.getSchoolClassName());
        }
        if (ObjectUtils.isNotEmpty(queryDTO.getCourseId())){
            schoolClassLambdaQueryWrapper.eq(SchoolClass::getCourseId, queryDTO.getCourseId());
        }
        schoolClassLambdaQueryWrapper.in(SchoolClass::getId, schoolClassIds);


        Page<SchoolClass> schoolClassPage = this.page(page, schoolClassLambdaQueryWrapper);
        List<SchoolClass> records = schoolClassPage.getRecords();
        List<SchoolClassVO> schoolClassVOS = BeanUtil.copyToList(records, SchoolClassVO.class);

        PageVO<SchoolClassVO> pageVO = new PageVO<>();
        BeanUtil.copyProperties(schoolClassPage, pageVO);
        pageVO.setRecords(schoolClassVOS);
        return pageVO;
    }

    @Override
    public PageVO<UserStudentVO> pageStudentList(SchoolClassStudentQueryDTO queryDTO) {
        Page<User> userPage = new Page<User>(queryDTO.getPageNumber(), queryDTO.getPageSize());
        List<UserSchoolClass> userSchoolClassList = userSchoolClassService
                .list(new LambdaQueryWrapper<UserSchoolClass>()
                        .eq(UserSchoolClass::getSchoolClassId, queryDTO.getSchoolClassId()));
        List<Long> userIds = userSchoolClassList.stream().map(UserSchoolClass::getUserId).collect(Collectors.toList());
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(queryDTO.getStudentName())){
            userLambdaQueryWrapper.like(User::getUsername, queryDTO.getStudentName());
        }
        if (ObjectUtils.isNotEmpty(queryDTO.getStuID())){
            userLambdaQueryWrapper.eq(User::getStuID, queryDTO.getStuID());
        }


        Page<User> page = userService.page(userPage, userLambdaQueryWrapper);
        PageVO<UserStudentVO> userStudentPageVO = new PageVO<>();
        BeanUtil.copyProperties(page, userStudentPageVO);
        List<User> records = page.getRecords();
        List<UserStudentVO> userStudentVOS = BeanUtil.copyToList(records, UserStudentVO.class);
        userStudentPageVO.setRecords(userStudentVOS);
        return userStudentPageVO;
    }
}
