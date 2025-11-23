package com.lixiaoyue.exception;

import com.lixiaoyue.common.BusinessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理（JDK8兼容）
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 处理业务异常
    @ExceptionHandler(BusinessException.class)
    public BusinessResponse<Void> handleBusinessException(BusinessException e) {
        log.error("业务异常：{}", e.getMessage());
        return BusinessResponse.fail(e.getCode(), e.getMessage());
    }

    // 处理参数校验异常（@Valid注解触发）
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BusinessResponse<Void> handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMsg = new StringBuilder("参数校验失败：");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMsg.append(fieldError.getField())
                    .append("：")
                    .append(fieldError.getDefaultMessage())
                    .append("，");
        }
        String msg = errorMsg.substring(0, errorMsg.length() - 1);
        log.error(msg);
        return BusinessResponse.fail(400, msg);
    }

    // 处理其他未知异常
    @ExceptionHandler(Exception.class)
    public BusinessResponse<Void> handleException(Exception e) {
        log.error("系统异常：", e);
        return BusinessResponse.fail("系统内部错误，请联系管理员");
    }
}
