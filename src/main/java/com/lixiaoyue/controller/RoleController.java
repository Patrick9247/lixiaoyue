package com.lixiaoyue.controller;


import cn.hutool.core.bean.BeanUtil;
import com.lixiaoyue.common.BusinessResponse;
import com.lixiaoyue.model.entity.Role;
import com.lixiaoyue.model.vo.RoleVO;
import com.lixiaoyue.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
