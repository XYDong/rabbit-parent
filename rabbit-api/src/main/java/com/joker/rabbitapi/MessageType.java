package com.joker.rabbitapi;

/**
 * @version 1.0.0
 * @ClassName MessageType.java
 * @Package com.joker.rabbitapi
 * @Author Joker
 * @Description 消息类型
 * @CreateTime 2021年08月30日 10:52:00
 */
public final class MessageType {

    /**
     * 迅速消息：不需要保障消息的可靠性，也不需要做消息的confirm确认
     */
    public static final String RAPID = "0";
    /**
     * 确认消息：不需要保障消息的可靠性，但是需要做消息的confirm确认
     */
    public static final String CONFIRM = "1";
    /**
     * 可靠性消息：一定要保障消息的100%可靠性投递，不允许有任何消息的丢失
     * ps:保障数据库和所发消息是原子性的（最终一致）
     */
    public static final String RELIANT = "2";
}
