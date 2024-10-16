package com.project.hunter.domain.dto;

public class RestResponse<T> {

    private int statusCode;
    private String message;
    private T data;
    private Object errors;

    public RestResponse() {
    }

    public RestResponse(int statusCode, String message, T data, Object errors) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }


}
