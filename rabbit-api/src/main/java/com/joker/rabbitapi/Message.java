package com.joker.rabbitapi;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0.0
 * @ClassName Message.java
 * @Package com.joker.rabbitapi
 * @Author Joker
 * @Description 消息实体
 * @CreateTime 2021年08月30日 10:44:00
 */
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = 6940907274280642272L;

    /**
     * 消息唯一id
     */
    private String messageId;
    /**
     * 消息主题
     */
    private String topic;

    /**
     * 路由规则
     */
    private String routingKey = "";

    /**
     * 消息的附加属性
     */
    private Map<String,Object> attributes = new HashMap<>();

    /**
     * 延迟消息的参数配置
     */
    private Integer delayMills;

    /**
     * 消息类型：默认是confirm消息
     */
    private String messageType = MessageType.CONFIRM;


    public Message() {
    }

    public Message(String messageId, String topic, String routingKey, Map<String, Object> attributes, Integer delayMills) {
        this.messageId = messageId;
        this.topic = topic;
        this.routingKey = routingKey;
        this.attributes = attributes;
        this.delayMills = delayMills;
    }
    public Message(String messageId, String topic, String routingKey, Map<String, Object> attributes, Integer delayMills, String messageType) {
        this.messageId = messageId;
        this.topic = topic;
        this.routingKey = routingKey;
        this.attributes = attributes;
        this.delayMills = delayMills;
        this.messageType = messageType;
    }
}
