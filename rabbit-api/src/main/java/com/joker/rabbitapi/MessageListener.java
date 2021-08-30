package com.joker.rabbitapi;

/**
 * @version 1.0.0
 * @ClassName MessageListener.java
 * @Package com.joker.rabbitapi
 * @Author Joker
 * @Description x消费者监听嘻嘻奥西
 * @CreateTime 2021年08月30日 11:14:00
 */
public interface MessageListener {
    void onMessage(Message message);

}
