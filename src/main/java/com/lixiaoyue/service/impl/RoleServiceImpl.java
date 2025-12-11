package com.lixiaoyue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.mapper.RoleMapper;
import com.lixiaoyue.model.entity.Role;
import com.lixiaoyue.model.vo.RoleVO;
import com.lixiaoyue.service.IRoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public RoleVO create(RoleVO roleVO) {
        Role role = new Role();
        BeanUtil.copyProperties(roleVO,role);
        boolean save = this.save(role);
        if (save) {
            roleVO.setId(role.getId());
            return roleVO;
        }
        return null;
    }
}
