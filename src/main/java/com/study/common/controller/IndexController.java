package com.study.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试一个接口两个调用路径
 *
 * @author zhangpba
 * @date 2021-08-18
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * 测试一个接口两个调用路径:下面两个路径均能访问
     * http://127.0.0.1:8815/index/index/0
     * http://127.0.0.1:8815/index/index
     *
     * @param count 参数
     * @return
     * @author zhangpba
     * @date 2021-08-18
     */
    @GetMapping({"/index", "/index/{count}"})
    public String testTread(@PathVariable(value = "count", required = false) Integer count) {
        logger.info("count为：{}", count);
        if (count != null) {
            return "count为：" + count;
        } else {
            return "count为空";
        }
    }
}
