package com.joker.rabbitcommon.serializer;

/**
 * @version 1.0.0
 * @ClassName SerializerFactory.java
 * @Package com.joker.rabbitcommon.serializer
 * @Author Joker
 * @Description 序列化工厂
 * @CreateTime 2021年08月30日 15:30:00
 */
public interface SerializerFactory {

    Serializer create();
}
