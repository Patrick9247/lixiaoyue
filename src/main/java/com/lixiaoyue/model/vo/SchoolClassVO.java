package com.lixiaoyue.model.vo;


import com.tangzc.mpe.autotable.annotation.ColumnComment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SchoolClassVO {

    @ColumnComment("班级名")
    private String schoolClassName;
    @ColumnComment("负责人Id(教师角色)")
    private Long dutyUserId ;
    @ColumnComment("开设状态:-开设中，-已关闭")
    private int status;
}
