package com.lixiaoyue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lixiaoyue.model.entity.Role;
import com.lixiaoyue.model.vo.RoleVO;

public interface IRoleService extends IService<Role> {
    RoleVO create(RoleVO roleVO);
}
