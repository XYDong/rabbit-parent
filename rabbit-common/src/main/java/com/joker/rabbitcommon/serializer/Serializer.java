package com.joker.rabbitcommon.serializer;

/**
 * @version 1.0.0
 * @ClassName Serializer.java
 * @Package com.joker.rabbitcommon.serializer
 * @Author Joker
 * @Description 序列化和反序列化的接口
 * @CreateTime 2021年08月30日 15:32:00
 */
public interface Serializer {

    /**
     * 方法描述: <br>
     * <p> 数据转化成字节数组 </p>
     *
     * @Author Joker
     * @CreateDate 2021/8/30 15:34
     * @param data 数据
     * @return byte
     * @ReviseName
     * @ReviseTime 2021/8/30 15:34
     **/
    byte[] serializeRaw(Object data);

    /**
     * 方法描述: <br>
     * <p> 数据转化为字符串 </p>
     *
     * @Author Joker
     * @CreateDate 2021/8/30 15:35
     * @param data 数据
     * @return String
     * @ReviseName
     * @ReviseTime 2021/8/30 15:35
     **/
    String serialize(Object data);


    /**
     * 方法描述: <br>
     * <p> 反序列化 </p>
     *
     * @Author Joker
     * @CreateDate 2021/8/30 15:35
     * @param content
     * @return T
     * @ReviseName
     * @ReviseTime 2021/8/30 15:35
     **/
    <T> T deserialize(String content);

    /**
     * 方法描述: <br>
     * <p> 反序列化 </p>
     *
     * @Author Joker
     * @CreateDate 2021/8/30 15:36
     * @param content
     * @return T
     * @ReviseName
     * @ReviseTime 2021/8/30 15:36
     **/
    <T> T deserialize(byte[] content);
}
