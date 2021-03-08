package com.study.common.service;

public interface IAsyncService {

    /**
     * spring的异步调用
     */
    void springAsync();

    /**
     * 同步方法
     */
    String sync();
}
