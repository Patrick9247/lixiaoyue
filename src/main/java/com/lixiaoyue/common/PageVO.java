package com.lixiaoyue.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class PageVO<T> {
    @ApiModelProperty(value = "每页显示的条数")
    private  Long size =10L;
    @ApiModelProperty(value = "当前页")
    private Long current=1L;
    @ApiModelProperty(value = "总条数")
    private Long total;
    @ApiModelProperty(value = "总页数")
    private Long pages;
    @ApiModelProperty(value = "分页数据")
    private List<T> records;
}
