package com.lixiaoyue.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一API返回结果（JDK8兼容）
 * @param <T> 响应数据类型
 */
@Data
@AllArgsConstructor
public class BusinessResponse<T> {
    private int code;       // 响应码（200成功，500失败，400参数错误）
    private String msg;     // 响应信息
    private T data;         // 响应数据

    // 成功响应（无数据）
    public static <T> BusinessResponse<T> success() {
        return new BusinessResponse<>(200, "操作成功", null);
    }

    // 成功响应（带数据）
    public static <T> BusinessResponse<T> success(T data) {
        return new BusinessResponse<>(200, "操作成功", data);
    }

    // 失败响应
    public static <T> BusinessResponse<T> fail(String msg) {
        return new BusinessResponse<>(500, msg, null);
    }

    // 自定义响应码
    public static <T> BusinessResponse<T> fail(int code, String msg) {
        return new BusinessResponse<>(code, msg, null);
    }
}