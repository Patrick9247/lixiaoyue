package com.lixiaoyue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lixiaoyue.model.entity.Homework;
import com.lixiaoyue.model.entity.Role;
import com.lixiaoyue.model.vo.HomeworkVO;

public interface IHomeworkService extends IService<Homework> {

    HomeworkVO publish(HomeworkVO homeworkVO);
}
