package com.joker.rabbitproducer.broker;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.joker.rabbitapi.Message;
import com.joker.rabbitapi.MessageType;
import com.joker.rabbitapi.exception.MessageException;
import com.joker.rabbitapi.exception.MessageRunTimeException;
import com.joker.rabbitcommon.serializer.SerializerFactory;
import com.joker.rabbitcommon.serializer.impl.JacksonSerializer;
import com.joker.rabbitcommon.serializer.impl.JacksonSerializerFactory;
import com.joker.rabbitproducer.convert.GenericMessageConverter;
import com.joker.rabbitproducer.convert.RabbitMessageConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 每个topic 对应一个RabbitTemplate
 * 1. 提高发送效率
 * 2. 可以根据不同的需求定制化不同的RabbitTemplate
 */

/**
 * @version 1.0.0
 * @ClassName RabbitTemplateContainer.java
 * @Package com.joker.rabbitproducer.broker
 * @Author Joker
 * @Description rabbitmq的池化操作
 * @CreateTime 2021年08月30日 14:33:00
 */
@Slf4j
@Component
public class RabbitTemplateContainer implements RabbitTemplate.ConfirmCallback {

    private final ConnectionFactory connectionFactory;

    private Splitter splitter = Splitter.on("#");

    private Map<String /*topic*/ , RabbitTemplate> rabbitMap = Maps.newConcurrentMap();

    private final SerializerFactory serializerFactory = JacksonSerializerFactory.INSTANCE;

    public RabbitTemplateContainer(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public RabbitTemplate getTemplate(Message message) throws MessageRunTimeException {
        Preconditions.checkNotNull(message);
        String topic = message.getTopic();
        RabbitTemplate rabbitTemplate = rabbitMap.get(topic);
        if (rabbitTemplate != null) {
            return rabbitTemplate;
        }
        log.info("#com.joker.rabbitproducer.broker.RabbitTemplateContainer.getTemplate# topic : {} is not exists, create one",topic);
        RabbitTemplate newTemplate = new RabbitTemplate(connectionFactory);
        newTemplate.setExchange(topic);
        newTemplate.setRoutingKey(message.getRoutingKey());
        // 对于message的序列化方式
        newTemplate.setMessageConverter(new RabbitMessageConvert(new GenericMessageConverter(serializerFactory.create())));
        // 除了迅速消息，都需要设置callback
        if (!MessageType.RAPID.equals(message.getMessageType())) {
            newTemplate.setConfirmCallback(this);
        }
        rabbitMap.put(topic,newTemplate);
        return newTemplate;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // 具体的消息应答
        Preconditions.checkNotNull(correlationData);
        List<String> list = splitter.splitToList(correlationData.getId());
        String messageId = list.get(0);
        Long sendTime = Long.valueOf(list.get(1));
        if (ack) {
            log.info("#com.joker.rabbitproducer.broker.RabbitTemplateContainer.confirm# send message is OK, confirm messageId: {},sendTime :{}",messageId,sendTime);
        } else {
            log.info("#com.joker.rabbitproducer.broker.RabbitTemplateContainer.confirm# send message is Fail, confirm messageId: {},sendTime :{},cause :{}",messageId,sendTime,cause);

        }
    }
}
