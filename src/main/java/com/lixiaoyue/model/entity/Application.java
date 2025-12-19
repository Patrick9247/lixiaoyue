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
@TableName("Application")
@Table
@Data
public class Application extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)  // 自增主键
    @ColumnComment("主键")
    private Long id;
    @ColumnComment("申请加入的班级Id")
    private Long schoolClassId;
    @ColumnComment("申请加入的班级名称")
    private String schoolClassName;
    @ColumnComment("申请加入的课程名称")
    private String courseName;
    @ColumnComment("申请人Id(学生Id)")
    private Long applicatorId;
    @ColumnComment("申请人姓名")
    private String applicatorName;
    @ColumnComment("负责人Id(教师角色)")
    private Long dutyUserId ;
    @ColumnComment("申请状态：未读/未通过/已通过")
    private Integer status;
    @ColumnComment("未通过理由")
    private String remark;
}
