package com.joker.rabbitproducer.service;

import com.joker.rabbitproducer.constant.BrokerMessageStatus;
import com.joker.rabbitproducer.entity.BrokerMessage;
import com.joker.rabbitproducer.mapper.BrokerMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/**
 * @version 1.0.0
 * @ClassName MessageStoreService.java
 * @Package com.joker.rabbitproducer.service
 * @Author Joker
 * @Description
 * @CreateTime 2021年08月31日 10:31:00
 */
public class MessageStoreService {
    private BrokerMessageMapper brokerMessageMapper;

    public MessageStoreService(BrokerMessageMapper brokerMessageMapper) {
        this.brokerMessageMapper = brokerMessageMapper;
    }

    public int insert(BrokerMessage brokerMessage){
        return brokerMessageMapper.insert(brokerMessage);
    }

    public BrokerMessage selectByMessageId(String messageId){
        return brokerMessageMapper.selectByPrimaryKey(messageId);
    }

    public void success(String messageId) {
        brokerMessageMapper.changeBrokerMessageStatus(messageId, BrokerMessageStatus.SEND_OK.getCode(), new Date());
    }

    public void failure(String messageId){
        brokerMessageMapper.changeBrokerMessageStatus(messageId, BrokerMessageStatus.SEND_FAIL.getCode(), new Date());
    }

    public List<BrokerMessage> fetchTimeOutMessage4Retry(BrokerMessageStatus status) {
        return brokerMessageMapper.queryBrokerMessageStatus4Timeout(status.getCode());
    }

    public int updatedTryCount(String messageId){
        return brokerMessageMapper.update4TryCount(messageId, new Date());
    }
}
