package com.lixiaoyue.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final int code;  // 异常码

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        this(500, message);
    }
}
