package com.joker.rabbitproducer.broker;

import com.google.common.base.Preconditions;
import com.joker.rabbitapi.Message;
import com.joker.rabbitapi.MessageProducer;
import com.joker.rabbitapi.MessageType;
import com.joker.rabbitapi.SendCallback;
import com.joker.rabbitapi.exception.MessageRunTimeException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version 1.0.0
 * @ClassName ProducerClient.java
 * @Package com.joker.rabbitproducer.broker
 * @Author Joker
 * @Description 消息发送端
 * @CreateTime 2021年08月30日 11:37:00
 */
@Component
public class ProducerClient implements MessageProducer {

    private final RabbitBroker rabbitBroker;

    public ProducerClient(RabbitBroker rabbitBroker) {
        this.rabbitBroker = rabbitBroker;
    }

    @Override
    public void send(Message message) throws MessageRunTimeException {
        Preconditions.checkNotNull(message.getTopic());
        switch (message.getMessageType()) {
            case MessageType.RAPID:
                rabbitBroker.rapidSend(message);
                break;
            case MessageType.CONFIRM:
                rabbitBroker.confirmSend(message);
                break;
            case MessageType.RELIANT:
                rabbitBroker.reliantSend(message);
                break;
            default:
                break;
        }
    }

    @Override
    public void send(List<Message> messages) throws MessageRunTimeException {
        for (Message message : messages) {
            switch (message.getMessageType()) {
                case MessageType.RAPID:
                    rabbitBroker.rapidSend(message);
                    break;
                case MessageType.CONFIRM:
                    rabbitBroker.confirmSend(message);
                    break;
                case MessageType.RELIANT:
                    rabbitBroker.reliantSend(message);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void send(Message message, SendCallback sendCallback) throws MessageRunTimeException {

    }
}
