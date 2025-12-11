package com.lixiaoyue.model.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tangzc.mpe.autotable.annotation.ColumnComment;

import java.util.Date;

public class BaseEntity {
    @ColumnComment("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    @ColumnComment("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)// 创建时间（JDK8兼容）
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtUpdate;
    @ColumnComment("逻辑删除")
    @TableField(fill = FieldFill.INSERT)
    private int deleted;
}
