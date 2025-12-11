package com.lixiaoyue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lixiaoyue.model.entity.Role;
import com.lixiaoyue.model.entity.SchoolClass;
import com.lixiaoyue.model.vo.SchoolClassVO;

public interface ISchoolClassService extends IService<SchoolClass> {
    SchoolClassVO create(SchoolClassVO schoolClassVO);

    Boolean join(Long schoolClassId, Long userId);
}
