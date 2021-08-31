package com.joker.rabbitproducer.constant;

/**
 * @version 1.0.0
 * @ClassName BrokerMessageStatus.java
 * @Package com.joker.rabbitproducer.constant
 * @Author Joker
 * @Description $BrokerMessageStatus 消息发送状态
 * @CreateTime 2021年08月31日 10:30:00
 */
public enum BrokerMessageStatus {

    SENDING("0"),
    SEND_OK("1"),
    SEND_FAIL("2"),
    SEND_FAIL_A_MOMENT("3"),
            ;

    private String code;

    BrokerMessageStatus(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
