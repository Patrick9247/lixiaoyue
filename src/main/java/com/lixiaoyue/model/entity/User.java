package com.lixiaoyue.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.tangzc.mpe.autotable.annotation.ColumnComment;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;  // JDK8使用java.util.Date（而非LocalDateTime）

/**
 * 用户表实体（与数据库表 backend_jdk8_template.user 映射）
 */

@EqualsAndHashCode(callSuper = true)
@TableName("user")
@Table
@Data
public class User extends BaseEntity{
    @TableId(type = IdType.ASSIGN_ID)  // 自增主键
    @ColumnComment("主键")
    private Long id;              // 用户ID
    @ColumnComment("用户名")
    private String username;      // 用户名（唯一）
    @ColumnComment("密码")
    private String password;      // 密码（实际项目需加密）
    @ColumnComment("昵称")
    private String nickname;      // 昵称
    @ColumnComment("年龄")
    private Integer age;          // 年龄
}