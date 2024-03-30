package com.deals.isodeals.exception;

public class GenericResponse<T> {
    public static final String SUCCESS_KEY = "Success";
    public static final String FAILED_KEY = "Failed";

    private String status;
    private String message;
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

}
