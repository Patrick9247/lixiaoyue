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
@TableName("UserSchoolClass")
@Table
@Data
public class UserSchoolClass extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @ColumnComment("id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @ColumnComment("班级id")
    private Long schoolClassId;
    @ColumnComment("学生id")
    private Long userId;

}
