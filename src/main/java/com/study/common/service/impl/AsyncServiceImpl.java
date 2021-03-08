package com.study.common.service.impl;

import com.study.common.service.IAsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步调用实现
 */
@Service
public class AsyncServiceImpl implements IAsyncService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    /**
     * spring的异步调用
     */
    @Async
    @Override
    public void springAsync() {
        logger.info("************ 执行异步start ************");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("************ 执行异步end ************");
    }

    /**
     * 同步方法
     *
     * @return 执行结果
     */
    @Override
    public String sync() {
        logger.info("************ 执同步方法 start ************");
        // 在此调用，失去了异步效果
        // springAsync();
        logger.info("************ 执同步方法 end ************");
        return "success";
    }
}
