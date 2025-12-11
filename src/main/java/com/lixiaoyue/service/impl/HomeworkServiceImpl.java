package com.lixiaoyue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.mapper.ApplicationMapper;
import com.lixiaoyue.mapper.HomeworkMapper;
import com.lixiaoyue.model.entity.Application;
import com.lixiaoyue.model.entity.Homework;
import com.lixiaoyue.model.vo.HomeworkVO;
import com.lixiaoyue.service.IApplicationService;
import com.lixiaoyue.service.IHomeworkDetailService;
import com.lixiaoyue.service.IHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
}
