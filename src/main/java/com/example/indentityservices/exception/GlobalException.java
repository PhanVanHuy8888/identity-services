package com.example.indentityservices.exception;

import com.example.indentityservices.dto.response.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseData handlingException(MethodArgumentNotValidException e) {
        String enumKey = e.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.USER_FIELD;
        return ResponseData.builder()
                .status(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

    @ExceptionHandler(value = AppException.class)
    ResponseData handlingAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseData.builder()
                .status(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }
//
//    @ExceptionHandler(value = RuntimeException.class)
//    ResponseData handlingRuntimeException(RuntimeException e) {
//        return ResponseData.builder()
//                .status(HttpStatus.NOT_FOUND.value())
//                .message("User not found 1111")
//                .build();
//    }
}
