package com.joker.rabbitcommon.serializer.impl;

import com.joker.rabbitapi.Message;
import com.joker.rabbitcommon.serializer.Serializer;
import com.joker.rabbitcommon.serializer.SerializerFactory;

/**
 * @version 1.0.0
 * @ClassName JacksonSerializerFactory.java
 * @Package com.joker.rabbitcommon.serializer.impl
 * @Author Joker
 * @Description 序列化和反序列化工厂
 * @CreateTime 2021年08月30日 15:41:00
 */
public class JacksonSerializerFactory implements SerializerFactory {

    public static final SerializerFactory INSTANCE = new JacksonSerializerFactory();

    @Override
    public Serializer create() {
        return JacksonSerializer.createParametricType(Message.class);
    }
}