package com.study.common.controller;

import com.study.common.service.IAsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异步调用
 */
@RestController
public class AsyncController {

    private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private IAsyncService asyncService;

    /**
     * 测试spring的异步调用顺序
     *
     * @return
     */
    @GetMapping("/async")
    public String asyncTest() {
        logger.info("************ 执行第一步 ************");
        asyncService.sync();
        asyncService.springAsync();
        logger.info("************ 执行第二步 ************");
        return "success";
    }

}
