package com.lixiaoyue.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.mpe.autotable.annotation.ColumnComment;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@TableName("user")
@Table
@Data
public class Role extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)  // 自增主键
    @ColumnComment("主键")
    private Long Id;
    @ColumnComment("角色名")
    private String roleName;
    @ColumnComment("角色描述")
    private String roleDesc;
}
