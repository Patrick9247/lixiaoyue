package com.lixiaoyue.common;


import lombok.Data;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

import java.io.Serializable;

@Data
public class BasePage extends SpringDataWebProperties.Pageable implements Serializable {
    private int pageNo = 1;
    private int pageSize = 20;
    private int totalCount;
    private int totalPage;

}
