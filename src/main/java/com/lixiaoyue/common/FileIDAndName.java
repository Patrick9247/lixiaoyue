package com.lixiaoyue.common;

import lombok.Data;

// 数据详情实体（匹配 data 数组中的单个对象）
@Data
public class FileIDAndName {
    private String name; // 匹配 JSON 中的 file_name
    private String id; // 匹配 JSON 中的 id
}
