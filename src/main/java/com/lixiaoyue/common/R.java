package com.lixiaoyue.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一API返回结果（JDK8兼容）
 * @param <T> 响应数据类型
 */
@Data
@AllArgsConstructor
public class R<T> {
    private int code;       // 响应码（200成功，500失败，400参数错误）
    private String msg;     // 响应信息
    private T data;         // 响应数据

    // 成功响应（无数据）
    public static <T> R<T> success() {
        return new R<>(200, "操作成功", null);
    }

    // 成功响应（带数据）
    public static <T> R<T> success(T data) {
        return new R<>(200, "操作成功", data);
    }

    // 失败响应
    public static <T> R<T> fail(String msg) {
        return new R<>(500, msg, null);
    }

    // 自定义响应码
    public static <T> R<T> fail(int code, String msg) {
        return new R<>(code, msg, null);
    }
}