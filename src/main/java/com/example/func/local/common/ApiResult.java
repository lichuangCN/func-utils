package com.example.func.local.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ApiResult<T> implements Serializable {

    private Integer code;

    private T data;

    private String message;

    public ApiResult() {
    }

    public ApiResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ApiResult(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public ApiResult(T data) {
        this.code = ErrorCode.SUCCESS.getCode();
        this.data = data;
    }

    public static ApiResult ok() {
        return ApiResult.builder()
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .build();
    }

    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult<T>(data);
    }

    public static ApiResult error(Integer code, String msg) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public static ApiResult fail() {
        return new ApiResult(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), null, ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
    }

    public static ApiResult fail(String msg) {
        return new ApiResult(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), null, msg);
    }

    public static ApiResult fail(ErrorCode errorCode) {
        return new ApiResult(errorCode.getCode(), null, errorCode.getMessage());
    }

    public boolean hasSuccess() {
        return this.code != null && ErrorCode.SUCCESS.getCode().equals(this.code);
    }
}
