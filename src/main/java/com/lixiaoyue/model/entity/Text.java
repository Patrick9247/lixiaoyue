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
@TableName("Text")
@Table
@Data
public class Text extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)  // 自增主键
    @ColumnComment("主键")
    private Long id;
    @ColumnComment("标题")
    private String title;
    @ColumnComment("内容")
    private String content ;
    @ColumnComment("所属人id")
    private Long userId;
    @ColumnComment("所属作业id")
    private Long homeworkId;
}
