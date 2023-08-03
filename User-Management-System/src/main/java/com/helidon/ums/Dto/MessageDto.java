package com.helidon.ums.Dto;

public class MessageDto {

    private String message;

    private Long userId;

    public MessageDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "message='" + message + '\'' +
                ", userId=" + userId +
                '}';
    }
}
