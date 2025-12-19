package com.lixiaoyue.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tangzc.mpe.autotable.annotation.ColumnComment;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@TableName("Homework")
@Table
@Data
public class Homework extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)  // 自增主键
    @ColumnComment("主键")
    private Long id;
    @ColumnComment("题目")
    private String title;
    @ColumnComment("内容")
    private String content;
    @ColumnComment("创建者id")
    private Long creatorId;
    @ColumnComment("创建者名")
    private String creatorName;
    @ColumnComment("所属课程")
    private String courseName;
    @ColumnComment("所属课程id")
    private Long courseId;
    @ColumnComment("所属班级id")
    private Long schoolClassId;
    @ColumnComment("所属班级名称")
    private Long schoolClassName;
    @ColumnComment("截止时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadline;
}
