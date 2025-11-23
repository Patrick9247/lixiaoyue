package com.lixiaoyue.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.mpe.autotable.annotation.ColumnComment;
import lombok.Data;


@Data
public class RoleVO {

    @TableId(type = IdType.ASSIGN_ID)  // 自增主键
    @ColumnComment("主键")
    private Long Id;
    @ColumnComment("角色名")
    private String roleName;
    @ColumnComment("角色描述")
    private String roleDesc;
}
