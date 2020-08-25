package com.study.business.people.controller;

import com.study.business.people.service.PeCityService;
import com.study.common.web.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangpba
 * @date 2020-06-15
 * @descript 市
 */
@RestController
@RequestMapping("/city")
public class PeCityController {

    private static final Logger logger = LoggerFactory.getLogger(PeCityController.class);

    @Autowired
    PeCityService cityService;

    // 初始化市数据
    @PutMapping("/init")
    public ResponseMessage init() {
        logger.info("进入init .....");
        return ResponseMessage.success(cityService.init());
    }

}
