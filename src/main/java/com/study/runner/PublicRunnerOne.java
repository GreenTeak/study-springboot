package com.study.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Order(2)
@Component
public class PublicRunnerOne implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(PublicRunnerOne.class);


    @Override
    public void run(String... args) throws Exception {
        logger.info("PublicRunnerOne.run() 项目启动后，需要执行的第二段功能写在这......");
    }
}
