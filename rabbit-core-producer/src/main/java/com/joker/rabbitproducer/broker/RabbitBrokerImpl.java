package com.joker.rabbitproducer.broker;

import com.joker.rabbitapi.Message;
import com.joker.rabbitapi.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @version 1.0.0
 * @ClassName RabbitBrokerImpl.java
 * @Package com.joker.rabbitproducer.broker
 * @Author Joker
 * @Description 不同种类型的消息的发送的实现
 * @CreateTime 2021年08月30日 14:07:00
 */
@Slf4j
@Component
public class RabbitBrokerImpl implements RabbitBroker{

    private final RabbitTemplateContainer templateContainer;

    public RabbitBrokerImpl(RabbitTemplateContainer templateContainer) {
        this.templateContainer = templateContainer;
    }

    @Override
    public void rapidSend(Message message) {
        message.setMessageType(MessageType.RAPID);
        sendKernel(message);
    }

    /**
     * 方法描述: <br>
     * <p> 发送消息的核心方法 </p>
     *
     * @Author Joker
     * @CreateDate 2021/8/30 14:10
     * @param message 消息体
     * @ReviseName
     * @ReviseTime 2021/8/30 14:10
     **/
    private void sendKernel(Message message) {
        AsyncBaseQueue.submit(() -> {
            RabbitTemplate template = templateContainer.getTemplate(message);
            String routingKey = message.getRoutingKey();
            String topic = message.getTopic();
            CorrelationData correlationData = new CorrelationData(String.format("%s#%s",message.getMessageId(),System.currentTimeMillis()));
            template.convertAndSend(topic,routingKey,message,correlationData);
            log.info("#com.joker.rabbitproducer.broker.RabbitBrokerImpl.sendKernel# send to rabbitmq, messageId: {}",message.getMessageId());
        });
    }

    @Override
    public void confirmSend(Message message) {
        message.setMessageType(MessageType.CONFIRM);
        sendKernel(message);
    }

    @Override
    public void reliantSend(Message message) {
        message.setMessageType(MessageType.RELIANT);
        sendKernel(message);
    }

    @Override
    public void sendMessages() {

    }
}
