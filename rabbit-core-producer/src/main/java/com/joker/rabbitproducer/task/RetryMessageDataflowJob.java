//package com.joker.rabbitproducer.task;
//
//import com.dangdang.ddframe.job.api.ShardingContext;
//import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
//import com.joker.rabbitproducer.broker.RabbitBroker;
//import com.joker.rabbitproducer.constant.BrokerMessageStatus;
//import com.joker.rabbitproducer.entity.BrokerMessage;
//import com.joker.rabbitproducer.service.MessageStoreService;
//import com.itihub.rabbit.task.annotaion.ElasticJobConfig;
//import com.itihub.rabbit.task.annotaion.JobCoreConfiguration;
//import com.itihub.rabbit.task.annotaion.LiteJobConfiguration;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @version 1.0.0
// * @ClassName RetryMessageDataflowJob.java
// * @Package com.joker.rabbitproducer.task
// * @Author Joker
// * @Description
// * @CreateTime 2021年08月31日 10:33:00
// */
//@Slf4j
//@ElasticJobConfig(
//        coreConfig = @JobCoreConfiguration(
//                name = "com.joker.rabbitproducer.task.RetryMessageDataflowJob",
//                cron = "0/10 * * * * ?",
//                description = "可靠性投递消息补偿任务",
//                shardingTotalCount = 1
//        ),
//        liteJobConfig = @LiteJobConfiguration(overwrite = true)
//
//)
//public class RetryMessageDataflowJob implements DataflowJob<BrokerMessage> {
//
//    private MessageStoreService messageStoreService;
//
//    private RabbitBroker rabbitBroker;
//
//    public RetryMessageDataflowJob(MessageStoreService messageStoreService, RabbitBroker rabbitBroker) {
//        this.messageStoreService = messageStoreService;
//        this.rabbitBroker = rabbitBroker;
//    }
//
//    private static final int MAX_RETRY_COUNT = 3;
//
//    /**
//     * 获取需要重试投递消息的数据
//     */
//    @Override
//    public List<BrokerMessage> fetchData(ShardingContext shardingContext) {
//        List<BrokerMessage> brokerMessageList = messageStoreService.fetchTimeOutMessage4Retry(BrokerMessageStatus.SENDING);
//        log.info("--------@@抓取数据集合,数量：{}", brokerMessageList.size());
//
//        return brokerMessageList;
//    }
//
//    /**
//     * 补偿投递消息
//     * @param shardingContext
//     * @param dataList
//     */
//    @Override
//    public void processData(ShardingContext shardingContext, List<BrokerMessage> dataList) {
//        dataList.forEach(brokerMessage -> {
//            // 判断消息投递次数是否已经超过最大努力重试次数
//            if (MAX_RETRY_COUNT <= brokerMessage.getTryCount()){
//                messageStoreService.failure(brokerMessage.getMessageId());
//                log.warn("消息最大努力重试最终失败，消息ID：{}", brokerMessage.getMessageId());
//            }else {
//                // 重试 更新try count字段
//                messageStoreService.updatedTryCount(brokerMessage.getMessageId());
//                rabbitBroker.reliantSend(brokerMessage.getMessage());
//            }
//        });
//    }
//}
