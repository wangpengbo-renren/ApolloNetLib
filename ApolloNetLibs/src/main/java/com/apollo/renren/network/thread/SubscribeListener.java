package com.apollo.renren.network.thread;

public interface SubscribeListener<T> {
    /**
     * 在工作线程
     * @return
     * @throws Exception
     */
    T runOnSubThread() throws Exception;
}
