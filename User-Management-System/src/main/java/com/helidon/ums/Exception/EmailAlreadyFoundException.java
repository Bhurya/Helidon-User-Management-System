package com.helidon.ums.Exception;

public class EmailAlreadyFoundException extends RuntimeException{


    public EmailAlreadyFoundException(String message) {
        super(message);
    }

    public EmailAlreadyFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
