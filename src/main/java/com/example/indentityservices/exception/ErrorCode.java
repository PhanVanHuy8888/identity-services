package com.example.indentityservices.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    USER_EXISTED(1001, "Email not existed"),
    USER_FIELD(1002, "Field user cannot blank")
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;
}
