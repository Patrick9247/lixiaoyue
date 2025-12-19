package com.lixiaoyue.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@ApiModel(value = "分页对象" ,description = "分页查询实体")
@NoArgsConstructor
public class PageDTO {
    @ApiModelProperty(value = "页码",required = true)
    private  Integer PageNumber=1;
    @ApiModelProperty(value = "每页显示数量",required = true)
    private  Integer PageSize=10;
    @ApiModelProperty("排序字段")
    private  String sortBy;
    @ApiModelProperty("排序方式")
    private  Boolean isAsc;

}

