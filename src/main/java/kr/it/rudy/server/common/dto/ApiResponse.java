package kr.it.rudy.server.common.dto;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private final boolean success;
    private final String message;
    private final T data;
    private final String error;

    public ApiResponse(T data) {
        this.success = true;
        this.data = data;
        this.message = "success";
        this.error = null;
    }
}
