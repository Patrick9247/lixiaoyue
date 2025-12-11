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
    private Long id;
    @ColumnComment("用户名")
    private String username;
    @ColumnComment("密码")
    private String password;
    @ColumnComment("昵称")
    private String nickname;
    @ColumnComment("头像")
    private String avatar;
    @ColumnComment("邮箱")
    private String email;
    @ColumnComment("描述")
    private String description;
    @ColumnComment("电话")
    private String phone;
    @ColumnComment("年龄")
    private Integer age;
    @ColumnComment("角色id")
    private Long roleId;
    @ColumnComment("所属班级Id")
    private Long schoolClassId;
    @ColumnComment("申请加入班级状态:待审核 / 已通过 / 已拒绝")
    private int schoolClassStatus;

}