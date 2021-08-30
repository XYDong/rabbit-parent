package com.joker.rabbitapi;

/**
 * @version 1.0.0
 * @ClassName SendCallback.java
 * @Package com.joker.rabbitapi
 * @Author Joker
 * @Description 消息发送后的回调
 * @CreateTime 2021年08月30日 11:15:00
 */
public interface SendCallback {

    /**
     * 方法描述: <br>
     * <p> 成功 </p>
     *
     * @Author Joker
     * @CreateDate 2021/8/30 11:22
     * @ReviseName
     * @ReviseTime 2021/8/30 11:22
     **/
    void onSuccess();

    /**
     * 方法描述: <br>
     * <p> 失败 </p>
     *
     * @Author Joker
     * @CreateDate 2021/8/30 11:22
     * @ReviseName
     * @ReviseTime 2021/8/30 11:22
     **/
    void onFailure();

}
