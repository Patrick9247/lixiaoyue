package com.lixiaoyue.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.mpe.autotable.annotation.ColumnComment;
import com.tangzc.mpe.autotable.annotation.Table;
import java.util.Date;  // JDK8使用java.util.Date（而非LocalDateTime）

/**
 * 用户表实体（与数据库表 backend_jdk8_template.user 映射）
 */

@TableName("user")
@Table
public class User extends BaseEntity{
    @TableId(type = IdType.AUTO)  // 自增主键
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
    @ColumnComment("创建时间")
    private Date gmtCreate;      // 创建时间（JDK8兼容）
    @ColumnComment("更新时间")
    private Date gmtUpdate;      // 更新时间（JDK8兼容）
}