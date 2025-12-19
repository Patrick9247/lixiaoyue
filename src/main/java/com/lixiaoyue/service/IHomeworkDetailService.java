package com.lixiaoyue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lixiaoyue.model.entity.Homework;
import com.lixiaoyue.model.entity.HomeworkDetail;
import com.lixiaoyue.model.entity.Role;
import com.lixiaoyue.model.vo.HomeworkDetailVO;
import com.lixiaoyue.model.vo.HomeworkVO;

public interface IHomeworkDetailService extends IService<HomeworkDetail> {
    Boolean create(Homework homework);

    HomeworkDetailVO finish(HomeworkDetailVO homeworkDetailVO);

    HomeworkDetailVO getHomeworkDetail(Long homeworkId, Long userId);
}
