package com.lixiaoyue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lixiaoyue.common.PageVO;
import com.lixiaoyue.model.dto.HomeworkQueryDTO;
import com.lixiaoyue.model.entity.Homework;
import com.lixiaoyue.model.entity.Role;
import com.lixiaoyue.model.vo.HomeworkDetailVO;
import com.lixiaoyue.model.vo.HomeworkFinishCheckVO;
import com.lixiaoyue.model.vo.HomeworkListVO;
import com.lixiaoyue.model.vo.HomeworkVO;

public interface IHomeworkService extends IService<Homework> {

    HomeworkVO publish(HomeworkVO homeworkVO);

    PageVO<HomeworkListVO> pageList(HomeworkQueryDTO queryDTO);

    HomeworkDetailVO finishCheck(HomeworkFinishCheckVO checkVO);
}
