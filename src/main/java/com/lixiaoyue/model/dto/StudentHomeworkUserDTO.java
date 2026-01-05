package com.lixiaoyue.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lixiaoyue.common.PageDTO;
import com.tangzc.mpe.autotable.annotation.ColumnComment;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StudentHomeworkUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userName;

    private Long homeworkId;

    private String homeworkTitle;

    private Integer status;
    private String file;
    private String size;
    private String device;
    private String grades;
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtSubmit;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCheck;
}
