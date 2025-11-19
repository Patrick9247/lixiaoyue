package com.lixiaoyue.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.mpe.autotable.annotation.ColumnComment;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    @TableId(type = IdType.AUTO)  // 自增主键
    @ColumnComment("主键")
    private Integer id;
    @ColumnComment("创建时间")
    private Date gmtCreate;      // 创建时间（JDK8兼容）
    @ColumnComment("更新时间")
    private Date gmtUpdate;
}
