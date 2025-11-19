package com.lixiaoyue.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;  // JDK8使用java.util.Date（而非LocalDateTime）

/**
 * 用户表实体（与数据库表 backend_jdk8_template.user 映射）
 */
@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)  // 自增主键
    private Long id;              // 用户ID
    private String username;      // 用户名（唯一）
    private String password;      // 密码（实际项目需加密）
    private String nickname;      // 昵称
    private Integer age;          // 年龄
    private Date createTime;      // 创建时间（JDK8兼容）
    private Date updateTime;      // 更新时间（JDK8兼容）
}