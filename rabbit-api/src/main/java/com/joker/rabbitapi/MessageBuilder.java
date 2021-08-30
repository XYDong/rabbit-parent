package com.joker.rabbitapi;

import com.joker.rabbitapi.exception.MessageRunTimeException;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @version 1.0.0
 * @ClassName MessageBuilder.java
 * @Package com.joker.rabbitapi
 * @Author Joker
 * @Description 建造者模式
 * @CreateTime 2021年08月30日 10:57:00
 */
public class MessageBuilder implements Serializable {
    private static final long serialVersionUID = 9079732407734655026L;

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

    public MessageBuilder() {
    }

    public static MessageBuilder create() {
        return new MessageBuilder();
    }

    private MessageBuilder withMessageId(String messageId) {
        this.messageId = messageId;
        return this;
    }
    private MessageBuilder withTopic(String topic) {
        this.topic = topic;
        return this;
    }
    private MessageBuilder withRoutingKey(String routingKey) {
        this.routingKey = routingKey;
        return this;
    }
    private MessageBuilder withAttribute(String key, Object value) {
        if (this.attributes == null) {
            attributes = new HashMap<>(4);
        }
        attributes.put(key, value);
        return this;
    }
    private MessageBuilder withMessageType(String messageType) {
        this.messageType = messageType;
        return this;
    }
    private MessageBuilder withAttributes(Map<String,Object> attributes) {
        this.attributes = attributes;
        return this;
    }
    private MessageBuilder withDelayMills(Integer delayMills) {
        this.delayMills = delayMills;
        return this;
    }

    private Message builder() {
        if (StringUtils.isBlank(messageId)) {
            messageId = UUID.randomUUID().toString().trim();
        }

        if (StringUtils.isBlank(topic)) {
            throw new MessageRunTimeException("this topic is null");
        }

        return new Message(messageId,topic,routingKey,attributes,delayMills);
    }


}
