package com.joker.rabbitproducer.broker;

import com.joker.rabbitapi.Message;

/**
 * @version 1.0.0
 * @ClassName RabbitBroker.java
 * @Package com.joker.rabbitproducer.broker
 * @Author Joker
 * @Description 不同种类型的消息
 * @CreateTime 2021年08月30日 14:04:00
 */
public interface RabbitBroker {
    /**
     * 方法描述: <br>
     * <p> 迅速消息 </p>
     *
     * @Author Joker
     * @CreateDate 2021/8/30 14:05
     * @param message 消息体
     * @ReviseName
     * @ReviseTime 2021/8/30 14:05
     **/
    void rapidSend(Message message);

    /**
     * 方法描述: <br>
     * <p> 确认消息 </p>
     *
     * @Author Joker
     * @CreateDate 2021/8/30 14:06
     * @param message 消息体
     * @ReviseName
     * @ReviseTime 2021/8/30 14:06
     **/
    void confirmSend(Message message);

    /**
     * 方法描述: <br>
     * <p> 延迟消息 </p>
     *
     * @Author Joker
     * @CreateDate 2021/8/30 14:06
     * @param message 消息体
     * @ReviseName
     * @ReviseTime 2021/8/30 14:06
     **/
    void reliantSend(Message message);


    void sendMessages();
}
