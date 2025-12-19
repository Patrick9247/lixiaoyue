package com.lixiaoyue.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tangzc.mpe.autotable.annotation.ColumnComment;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@TableName("HomeworkDetail")
@Table
@Data
public class HomeworkDetail extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)  // 自增主键
    @ColumnComment("主键")
    private Long id;
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
    @ColumnComment("提交时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtSubmit;
    @ColumnComment("批改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCheck;

}
