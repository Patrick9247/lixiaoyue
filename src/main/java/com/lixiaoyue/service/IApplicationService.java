package com.lixiaoyue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lixiaoyue.common.PageVO;
import com.lixiaoyue.model.dto.ApplicationDTO;
import com.lixiaoyue.model.dto.ApplicationQueryDTO;
import com.lixiaoyue.model.dto.SchoolClassJoinDTO;
import com.lixiaoyue.model.entity.Application;
import com.lixiaoyue.model.entity.Course;
import com.lixiaoyue.model.vo.ApplicationVO;

public interface IApplicationService extends IService<Application> {
    Boolean handle(ApplicationDTO applicationDTO);

    PageVO<ApplicationVO> pageList(ApplicationQueryDTO queryDTO);

    Boolean apply(SchoolClassJoinDTO schoolClassJoinDTO);
}
