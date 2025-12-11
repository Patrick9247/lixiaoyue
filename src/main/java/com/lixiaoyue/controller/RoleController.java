package com.lixiaoyue.controller;


import cn.hutool.core.bean.BeanUtil;
import com.lixiaoyue.common.BusinessResponse;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.model.entity.Role;
import com.lixiaoyue.model.vo.RoleVO;
import com.lixiaoyue.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    IRoleService roleService;

    @GetMapping("/all")
    @Operation(description = "查询所有角色")
    public BusinessResponse<ResponseEntity<List<RoleVO>>> allRole(){
        List<Role> list = roleService.list();
        List<RoleVO> roleVOS = BeanUtil.copyToList(list, RoleVO.class);
        return BusinessResponse.success(ResponseEntity.ok(roleVOS));
    }

    @PostMapping("/create")
    @Operation(summary = "创建角色")
    public BusinessResponse<RoleVO> create(@RequestBody RoleVO roleVO) {
        roleVO.setId(null);
        RoleVO created = roleService.create(roleVO);
        if (created == null) {
            throw new BusinessException("角色创建失败！");
        }
        return BusinessResponse.success(created);
    }
}
