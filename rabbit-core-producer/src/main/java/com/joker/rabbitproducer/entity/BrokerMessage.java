package com.joker.rabbitproducer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0.0
 * @ClassName BrokerMessage.java
 * @Package com.joker.rabbitproducer.entity
 * @Author Joker
 * @Description 消息记录表实体映射
 * @CreateTime 2021年08月31日 10:09:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrokerMessage implements Serializable {

    private static final long serialVersionUID = 7595620934559360495L;

    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 消息体
     */
    private String message;

    /**
     * 重试次数
     */
    private Integer tryCount = 0;

    /**
     * 消息状态(0.发送中 1.发送成功 2.发送失败)
     */
    private String status;

    /**
     * 下次尝试时间点
     */
    private Date nextRetry;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
