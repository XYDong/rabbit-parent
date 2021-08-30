package com.joker.rabbitproducer.broker;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @version 1.0.0
 * @ClassName AsyncBaseQueue.java
 * @Package com.joker.rabbitproducer.broker
 * @Author Joker
 * @Description 异步队列
 * @CreateTime 2021年08月30日 14:20:00
 */
@Slf4j
public class AsyncBaseQueue {

    public static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors();

    public static final int QUEUE_SIZE = 10000;

    private static ExecutorService senderAsync = new ThreadPoolExecutor(THREAD_SIZE,
            THREAD_SIZE,
            60L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(QUEUE_SIZE),
            r -> {
                Thread t = new Thread(r);
                t.setName("rabbitmq_client_async_sender");
                return null;
            },
            (r, executor) -> log.error("async sender is error rejected,runnable :{}, executor : {}",r,executor));

    /**
     * 方法描述: <br>
     * <p> 异步提交 </p>
     *
     * @Author Joker
     * @CreateDate 2021/8/30 14:27
     * @param runnable 执行体
     * @ReviseName
     * @ReviseTime 2021/8/30 14:27
     **/
    public static void  submit(Runnable runnable) {
        senderAsync.submit(runnable);
    }
}
