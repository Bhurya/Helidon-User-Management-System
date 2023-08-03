package com.helidon.ums.Entity;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.persistence.*;

@Entity
@Table(name = "messages")
@JsonbPropertyOrder({"messageId", "message","user"})
public class Message {

    private Long messageId;

    private String message;

    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", message='" + message + '\'' +
                ", user=" + user +
                '}';
    }
}
