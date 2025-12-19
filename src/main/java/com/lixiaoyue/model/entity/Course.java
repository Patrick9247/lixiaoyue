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
@TableName("Course")
@Table
@Data
public class Course extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)  // 自增主键
    @ColumnComment("主键")
    private Long id;
    @ColumnComment("课程名")
    private String courseName;
    @ColumnComment("负责人Id(教师角色)")
    private Long dutyUserId ;
    @ColumnComment("开设状态:-开设中，-已关闭")
    private Integer status;
    @ColumnComment("课程描述")
    private String remark;
}
