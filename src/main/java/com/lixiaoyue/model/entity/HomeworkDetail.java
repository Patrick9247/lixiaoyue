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
@TableName("HomeworkDetail")
@Table
@Data
public class HomeworkDetail extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)  // 自增主键
    @ColumnComment("主键")
    private Long Id;
    @ColumnComment("作业id")
    private Long homeworkId;
    @ColumnComment("老师id")
    private Long creatorId;
    @ColumnComment("作业题目")
    private String homeworkTitle;
    @ColumnComment("学生id")
    private Long ownerId;
    @ColumnComment("完成状态")
    private Integer status;
    @ColumnComment("文件地址")
    private String file;
    @ColumnComment("文件大小")
    private String size;
    @ColumnComment("提交设备")
    private String device;
    @ColumnComment("成绩")
    private String grades;
    @ColumnComment("教师评语")
    private String remark;

}
