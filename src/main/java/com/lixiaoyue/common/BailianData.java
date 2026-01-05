package com.lixiaoyue.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// 顶层响应实体（匹配整个 JSON 结构）
@Data
public class BailianData {
    private Integer code; // 匹配 JSON 中的 code
    private List<FileDetail> data; // 匹配 JSON 中的 data 数组（关键：使用 List 集合）
    private String message; // 匹配 JSON 中的 message
}

