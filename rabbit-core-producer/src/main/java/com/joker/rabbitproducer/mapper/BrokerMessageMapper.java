package com.joker.rabbitproducer.mapper;

import com.joker.rabbitproducer.entity.BrokerMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0.0
 * @ClassName BrokerMessageMapper.java
 * @Package com.joker.rabbitproducer.mapper
 * @Author Joker
 * @Description
 * @CreateTime 2021年08月31日 10:13:00
 */
@Mapper
public interface BrokerMessageMapper {
    int deleteByPrimaryKey(String messageId);

    int insert(BrokerMessage record);

    int insertSelective(BrokerMessage record);

    BrokerMessage selectByPrimaryKey(String messageId);

    int updateByPrimaryKeySelective(BrokerMessage record);

    int updateByPrimaryKeyWithBLOBs(BrokerMessage record);

    int updateByPrimaryKey(BrokerMessage record);

    void changeBrokerMessageStatus(@Param("brokerMessageId") String brokerMessageId,
                                   @Param("brokerMessageStatus") String brokerMessageStatus,
                                   @Param("updateTime") Date updateTime);

    List<BrokerMessage> queryBrokerMessageStatus4Timeout(@Param("brokerMessageStatus") String brokerMessageStatus);

    List<BrokerMessage> queryBrokerMessageStatus(@Param("brokerMessageStatus") String brokerMessageStatus);

    int update4TryCount(@Param("brokerMessageId") String brokerMessageId, @Param("updateTime") Date updateTime);
}
