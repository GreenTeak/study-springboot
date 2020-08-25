package com.study.business.people.controller;

import com.alibaba.fastjson.JSON;
import com.study.business.people.entity.PeProvince;
import com.study.business.people.service.PeProvinceService;
import com.study.common.web.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangpba
 * @date 2020-06-15
 * @descript 省
 */
@RestController
@RequestMapping("/province")
public class PeProvinceController {
    private static final Logger logger = LoggerFactory.getLogger(PeProvinceController.class);

    @Autowired
    private PeProvinceService provinceService;

    // 根据省代码查询
    @GetMapping("/findByCode")
    public ResponseMessage findByCode(@RequestParam(value = "code", required = true) Integer code) {
        logger.info("进入查询 根据省代码.....");
        return ResponseMessage.success(provinceService.findAllByProvinceCode(code));
    }

    // 根据省代码查询
    @GetMapping("/findByName")
    public ResponseMessage findByName(@RequestParam(value = "name", required = true) String name) {
        logger.info("进入查询 根据省名称.....");
        return ResponseMessage.success(provinceService.findAllByProvinceName(name));
    }

    // 查询所有
    @GetMapping("/findAll")
    public ResponseMessage findAll() {
        logger.info("进入查询 根据省名称.....");
        return ResponseMessage.success(provinceService.findAll());
    }

    // 根据id查询
    @GetMapping("/findById")
    public ResponseMessage findById(@RequestParam(value = "id", required = true) Long id) {
        logger.info("进入查询 根据ID.....");
        return ResponseMessage.success(provinceService.findById(id));
    }

    // 添加信息
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody String provinceStr) {
        logger.info("进入新增.....");
        PeProvince province = JSON.parseObject(provinceStr, PeProvince.class);

        provinceService.save(province);
        return ResponseMessage.success("save success");
    }

    // 删除信息
    @DeleteMapping("/delete")
    public ResponseMessage delete(@RequestParam(value = "id", required = true) Long id) {
        logger.info("进入查询 根据ID.....");
        provinceService.delete(id);
        return ResponseMessage.success("save success");
    }


    // 更新信息
    @PostMapping("/update")
    public ResponseMessage update(PeProvince province) {
        logger.info("进入查询.....");
        provinceService.update(province);
        return ResponseMessage.success("save success");
    }

    // 分页操作
    @GetMapping("/query")
    public ResponseMessage query(@RequestParam(value = "page", required = true) Integer page,
                                 @RequestParam(value = "size", required = true) Integer size) {
        logger.info("进入查询 分页page={},size={}.....", page, size);

        if (page == null || page <= 0) {
            page = 0;
        } else {
            page = page - 1;
        }
        return ResponseMessage.success(provinceService.findAll(page, size));
    }

    // 初始化省数据
    @PutMapping("/init")
    public ResponseMessage init() {
        logger.info("进入init .....");
        return ResponseMessage.success(provinceService.init());
    }

}
