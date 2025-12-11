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
@TableName("SchoolClass")
@Table
@Data
public class SchoolClass extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)  // 自增主键
    @ColumnComment("主键")
    private Long Id;
    @ColumnComment("班级名")
    private String schoolClassName;
    @ColumnComment("负责人Id(教师角色)")
    private Long dutyUserId ;
    @ColumnComment("开设状态:-开设中，-已关闭")
    private int status;
    @ColumnComment("所属课程名")
    private String courseName;
    @ColumnComment("所属课程id")
    private Long courseId;
}
