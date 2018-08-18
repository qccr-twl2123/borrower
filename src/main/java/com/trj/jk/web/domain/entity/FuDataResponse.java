package com.trj.jk.web.domain.entity;

public class FuDataResponse<T> {

    private static final int SUCCESS = 0;
    private static final int ERROR = 1;

    private int code;
    private T data;

    private FuDataResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public static <T> FuDataResponse<T> success(T data) {
        return new FuDataResponse<T>(SUCCESS, data);
    }

    public static FuDataResponse<String> error(String message) {
        return new FuDataResponse<String>(ERROR, message);
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

}
