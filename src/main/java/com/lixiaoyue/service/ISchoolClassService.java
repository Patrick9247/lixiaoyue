package com.lixiaoyue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lixiaoyue.common.PageVO;
import com.lixiaoyue.model.dto.MySchoolClassQueryDTO;
import com.lixiaoyue.model.dto.SchoolClassQueryDTO;
import com.lixiaoyue.model.dto.SchoolClassStudentQueryDTO;
import com.lixiaoyue.model.entity.Role;
import com.lixiaoyue.model.entity.SchoolClass;
import com.lixiaoyue.model.vo.SchoolClassVO;
import com.lixiaoyue.model.vo.UserStudentVO;

public interface ISchoolClassService extends IService<SchoolClass> {
    SchoolClassVO create(SchoolClassVO schoolClassVO);

    Boolean join(Long schoolClassId, Long userId);

    PageVO<SchoolClassVO> pageList(SchoolClassQueryDTO queryDTO);

    PageVO<SchoolClassVO> pageMyList(MySchoolClassQueryDTO queryDTO);

    PageVO<UserStudentVO> pageStudentList(SchoolClassStudentQueryDTO queryDTO);
}
