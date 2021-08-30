package com.joker.rabbitproducer.convert;

import com.google.common.base.Preconditions;
import com.joker.rabbitcommon.serializer.Serializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @version 1.0.0
 * @ClassName GenericMessageConverter.java
 * @Package com.joker.rabbitproducer.convert
 * @Author Joker
 * @Description 消息的序列化
 * @CreateTime 2021年08月30日 15:46:00
 */
public class GenericMessageConverter implements MessageConverter {

    private Serializer serializer;

    public GenericMessageConverter(Serializer serializer) {
        Preconditions.checkNotNull(serializer);
        this.serializer = serializer;
    }

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        byte[] bytes = this.serializer.serializeRaw(object);
        return new Message(bytes, messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        return this.serializer.deserialize(message.getBody());
    }
}
