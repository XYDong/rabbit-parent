package com.joker.rabbitapi;

import com.joker.rabbitapi.exception.MessageRunTimeException;

import java.util.List;

/**
 * @version 1.0.0
 * @ClassName MessageProducer.java
 * @Package com.joker.rabbitapi
 * @Author Joker
 * @Description 消息发送接口
 * @CreateTime 2021年08月30日 11:13:00
 */
public interface MessageProducer {

    /**
     * 方法描述: <br>
     * <p> message消息发送，无回调处理 </p>
     *
     * @Author Joker
     * @CreateDate 2021/8/30 11:18
     * @param message 消息体
     * @ReviseName
     * @ReviseTime 2021/8/30 11:18
     **/
    void send(Message message) throws MessageRunTimeException;


    /**
     * 方法描述: <br>
     * <p> 批量发送消息 </p>
     *
     * @Author Joker
     * @CreateDate 2021/8/30 11:20
     * @param messages 消息列表
     * @ReviseName
     * @ReviseTime 2021/8/30 11:20
     **/
    void send(List<Message> messages) throws MessageRunTimeException;

    /**
     * 方法描述: <br>
     * <p> 消息的发送，附带callback回调执行响应的业务逻辑处理 </p>
     *
     * @Author Joker
     * @CreateDate 2021/8/30 11:17
     * @param message 消息实体
     * @param sendCallback 回调
     * @ReviseName
     * @ReviseTime 2021/8/30 11:17
     **/
    void send(Message message, SendCallback sendCallback) throws MessageRunTimeException;

}
