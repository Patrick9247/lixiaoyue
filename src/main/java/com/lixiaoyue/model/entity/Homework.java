package com.lixiaoyue.model.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.mpe.autotable.annotation.ColumnComment;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@TableName("Homework")
@Table
@Data
public class Homework extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)  // 自增主键
    @ColumnComment("主键")
    private Long Id;
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
    @ColumnComment("作业文件地址")
    private List<String> files;
}
