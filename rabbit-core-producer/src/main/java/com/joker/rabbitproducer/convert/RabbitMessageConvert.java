package com.joker.rabbitproducer.convert;

import com.google.common.base.Preconditions;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @version 1.0.0
 * @ClassName RabbitMessageConvert.java
 * @Package com.joker.rabbitproducer.convert
 * @Author Joker
 * @Description 代理序列化
 * @CreateTime 2021年08月30日 15:59:00
 */
public class RabbitMessageConvert implements MessageConverter {
    private GenericMessageConverter delegate;

    private final String delaultExprie = String.valueOf(24 * 60 * 60 * 1000);

    public RabbitMessageConvert(GenericMessageConverter genericMessageConverter) {
        Preconditions.checkNotNull(genericMessageConverter);
        this.delegate = genericMessageConverter;
    }

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        messageProperties.setExpiration(delaultExprie);
        return delegate.toMessage(object,messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        return  (com.joker.rabbitapi.Message) this.delegate.fromMessage(message);
    }
}
