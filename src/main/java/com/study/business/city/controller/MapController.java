package com.study.business.city.controller;

import com.study.common.constant.ComConstant;
import com.study.business.city.entity.City;
import com.study.business.city.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 地图控制层
 * 2019-11-29
 */
@RestController
@RequestMapping("/map")
public class MapController {
    private static final Logger logger = LoggerFactory.getLogger(MapController.class);
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CityService cityService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 经纬度查找-利用开发api
    @GetMapping("/get/{jingwei}")
    public String getMap(@PathVariable(value = "jingwei", required = true) String jingwei) {
        // 坐标（28.696117043877,115.95845796638）对应的地址是：江西省南昌市青山湖区创新路1号
        String url = ComConstant.baidu_Api_Url + jingwei + ComConstant.baidu_Api_Param;
        return restTemplate.getForObject(url, String.class);
    }

    // 查询全国地区名称-利用开发api
    // TODO 乱码未处理 2019-12-30
    @GetMapping("/get")
    public String get() {
        String url = ComConstant.map_url;
        String result = restTemplate.getForObject(url, String.class);
        logger.info("result" + result);
        // 将ANSI转成UTF-8
        String out = null;
        try {
            out = new String(result.getBytes("UTF-8"), "GBK");
        } catch (Exception e) {
            logger.info("转码失败");
        }
        return out;
    }


    // 从redis中获取市区信息，并插入数据库city表中
    @GetMapping("/read")
    public String getRedis() {
        String result = stringRedisTemplate.opsForValue().get("city");
        logger.info(result);
        List<City> cities = cityService.parse(result);
        cityService.batchAddCity(cities);
        return result;
    }

    // 通过省名查询省下的所有市
    @GetMapping("/citys")
    public List<City> getCityByProvince(@RequestParam(value = "province", required = true) String province) {
        List<City> cities = cityService.getCityByProvince(province);
        return cities;
    }

    /**
     * 测试springboot集成redis成功
     */
    @GetMapping("/set")
    public void set() {
        stringRedisTemplate.opsForValue().set("spring", "springboot");
        String result = stringRedisTemplate.opsForValue().get("spring");
        logger.info(result);
    }

}
