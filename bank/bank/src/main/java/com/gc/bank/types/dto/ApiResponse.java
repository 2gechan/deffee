package com.gc.bank.types.dto;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    int code;
    String message;
    T result;

    public ApiResponse(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public ApiResponse() {

    }

    public static <T> ApiResponse<T> success(T result) {
        return new ApiResponse<T>(HttpStatus.OK.value(), "SUCCESS", result);
    }

    public static <T> ApiResponse<T> failed(HttpStatus code, String message, T result) {
        return new ApiResponse<T>(code.value(), message, result);
    }


}
