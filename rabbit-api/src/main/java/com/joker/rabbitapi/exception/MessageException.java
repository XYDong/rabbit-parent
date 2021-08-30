package com.joker.rabbitapi.exception;

/**
 * @version 1.0.0
 * @ClassName MessageException.java
 * @Package com.joker.rabbitapi.exception
 * @Author Joker
 * @Description 消息异常
 * @CreateTime 2021年08月30日 11:07:00
 */
public class MessageException extends Exception{
    private static final long serialVersionUID = -5847170131099270274L;

    public MessageException() {
    }

    public MessageException(String message) {
        super(message);
    }

    public MessageException(Throwable cause) {
        super(cause);
    }
    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
