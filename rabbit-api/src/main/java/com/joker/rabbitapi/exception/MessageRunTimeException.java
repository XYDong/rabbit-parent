package com.joker.rabbitapi.exception;

/**
 * @version 1.0.0
 * @ClassName MessageRunTimeException.java
 * @Package com.joker.rabbitapi.exception
 * @Author Joker
 * @Description
 * @CreateTime 2021年08月30日 11:09:00
 */
public class MessageRunTimeException extends RuntimeException{
    private static final long serialVersionUID = 9198422507445421862L;

    public MessageRunTimeException() {
    }

    public MessageRunTimeException(String message) {
        super(message);
    }

    public MessageRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageRunTimeException(Throwable cause) {
        super(cause);
    }

    public MessageRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
