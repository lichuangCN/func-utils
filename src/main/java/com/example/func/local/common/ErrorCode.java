package com.example.func.local.common;

import lombok.Getter;

/**
 * @author lichuang
 * @date 2021/10/29
 */
@Getter
public enum ErrorCode {

    SUCCESS(20000, "成功"),
    INTERNAL_SERVER_ERROR(50000, "服务内部错误"),
    ;

    private Integer code;

    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
