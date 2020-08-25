package com.study.common.controller;

import com.study.common.service.ThreadPoolService;
import com.study.common.service.impl.ThreadPoolServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/thread")
public class ThreadController {
    private static final Logger logger = LoggerFactory.getLogger(ThreadController.class);
    @Autowired
    private ThreadPoolService threadPoolService;

    @GetMapping("/pool")
    public String testTread(@RequestParam(value = "poolType", required = true) String poolType) {
        logger.info("传入参数 poolType：{}", poolType);
        threadPoolService.test(poolType);
        return "线程测试成功";
    }


    @GetMapping("/ex")
    public void testException() {
        try {
            threadPoolService.testException();
        } catch (Exception e) {
            logger.info("测试异常：{}", e.getMessage());
            logger.info("测试信息：{}", e);
        }

        System.out.println("继续走了");

    }

}
