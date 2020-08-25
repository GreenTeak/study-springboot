package com.study.business.city.controller;

import com.study.common.constant.ComConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/tel")
public class TelephonController {

    @Autowired
    private RestTemplate restTemplate;

    // 通过taobao网api查询手机归属地
    @GetMapping("/get/{phoNum}")
    public String test(@PathVariable(value = "phoNum", required = true) String phoNum) {
        return restTemplate.getForObject(ComConstant.tabao_Api + phoNum, String.class);
    }
}
