package com.helidon.ums.Response;


import jakarta.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"status","message","code","data"})
public class ApiResponse {

    private boolean status;

    private String message;

    private Long code;

    private Object data;

    public ApiResponse() {
    }


    public ApiResponse(boolean status, String message, Object data, Long code) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public ApiResponse(boolean status, String message, Long code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
