package com.study.business.people.controller;

import com.study.business.people.entity.PeAddress;
import com.study.business.people.service.PeAddressService;
import com.study.common.web.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangpba
 * @date 2020-06-16
 * @descript 地址
 */
@RestController
@RequestMapping("/address")
public class PeAddressController {

    private static final Logger logger = LoggerFactory.getLogger(PeAddressController.class);

    @Autowired
    private PeAddressService addressService;


    /**
     * 增肌用户地址
     *
     * @param provinceCode
     * @param cityCode
     * @param address
     * @return
     */
    @PostMapping("/add")
    public ResponseMessage add(@RequestParam(value = "provinceCode", required = true) Long provinceCode,
                               @RequestParam(value = "cityCode", required = true) Long cityCode,
                               @RequestParam(value = "address", required = true) String address) {
        logger.info("add provinceCode:{},cityCode:{},address:{} .....", provinceCode, cityCode, address);

        PeAddress peAddress = new PeAddress();
        peAddress.setProvinceCode(provinceCode);
        peAddress.setCityCode(cityCode);
        peAddress.setAddress(address);
        addressService.save(peAddress);
        return ResponseMessage.success();
    }

}
