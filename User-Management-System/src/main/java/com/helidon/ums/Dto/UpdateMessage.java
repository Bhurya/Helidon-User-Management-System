package com.helidon.ums.Dto;

public class UpdateMessage {

    private Long messageId;

    private String message;

    private Long userId;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
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
        return "UpdateMessage{" +
                "messageId=" + messageId +
                ", message='" + message + '\'' +
                ", userId=" + userId +
                '}';
    }
}
