package com.lixiaoyue.common;

import lombok.Data;

// 数据详情实体（匹配 data 数组中的单个对象）
@Data
public class FileDetail {
    private String file_name; // 匹配 JSON 中的 file_name
    private Long file_size; // 匹配 JSON 中的 file_size
    private String status; // 匹配 JSON 中的 status
    private String message; // 匹配 JSON 中的 message
    private String id; // 匹配 JSON 中的 id
    private String url; // 匹配 JSON 中的 url
}
