package com.study.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

/**
 * @Description: 定义监听类
 * @Author: zhangpb
 * @Date: 2019-11-14
 */
public class ApplicationEventListener implements ApplicationListener {

    private Logger logger = LoggerFactory.getLogger(ApplicationEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            logger.info("监听器 环境初始化！！！");
        } else if (event instanceof ApplicationPreparedEvent) {
            logger.info("监听器 初始化完成！！！");
        } else if (event instanceof ContextRefreshedEvent) {
            logger.info("监听器 应用刷新！！！");
        } else if (event instanceof ApplicationReadyEvent) {
            logger.info("监听器 项目启动完成！！！");
        } else if (event instanceof ContextStartedEvent) {
            logger.info("监听器 应用启动！！！");
        } else if (event instanceof ContextStoppedEvent) {
            logger.info("监听器 项目停止！！！");
        } else if (event instanceof ContextClosedEvent) {
            logger.info("监听器 应用关闭！！！");
        }
    }
}
