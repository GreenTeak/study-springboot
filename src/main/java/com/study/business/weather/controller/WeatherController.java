package com.study.business.weather.controller;

import com.study.business.city.entity.City;
import com.study.business.city.service.CityService;
import com.study.business.weather.entity.Weather;
import com.study.business.weather.model.WeatherModel;
import com.study.business.weather.service.WeatherService;
import com.study.common.util.DateUtil;
import com.study.common.web.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/weather")
@EnableScheduling
public class WeatherController {
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private CityService cityService;

    // Value的取值，当能找到weather.city时，取对应的值；如果没有找到，取冒号":"后边的默认值
    @Value("${weather.city:西安,宝鸡}")
    private String citys;

    /**
     * 插入一个城市的天气预报
     *
     * @param city
     */
    @GetMapping("/put")
    public void getWeatherFromBaidu(@RequestParam(value = "city", required = true) String city) {
        weatherService.getWeatherFromBaidu(city);
    }

    // 查询天气预报
    @GetMapping("/get")
    public ResponseMessage getWeatherModels() {
        List<WeatherModel> weatherModels = weatherService.getWeatherModels();
        return ResponseMessage.success(weatherModels);
    }

    // 插入全国城市的天气预报 2019-12-05
    @GetMapping("/all")
    public void insertWeather() {
        // 查出所有的城市
        List<City> cities = cityService.getAllCity();
        // 查出天气预报，并插入数据库
        if (cities != null && cities.size() > 0) {
            cities.forEach(city -> {
                weatherService.getWeatherFromBaidu(city.getCityName());
            });
        }
    }

    // 根据天气预报id删除天气预报和明细 2019-12-26
    @DeleteMapping("/delete")
    public ResponseMessage delete(@RequestParam(value = "ids", required = true) String idString) {
        logger.info("ids:{}", idString);
        String[] idsStr = idString.split(",");
        List<Long> list = new ArrayList<>();
        Arrays.asList(idsStr).forEach(idStr -> {
            Long is = Long.parseLong(idStr);
            list.add(is);
        });
        Long[] ids = list.toArray(new Long[list.size()]);
        weatherService.deleteWeatherByWeatherId(ids);
        return ResponseMessage.success();
    }

    // 获取关心的城市的天气预报(关心的城市在配置文件中配置) 2019-12-26
    @GetMapping("/care")
    public ResponseMessage getWeather() {
        String[] cityArray = citys.split(",");
        Arrays.asList(cityArray).forEach(city -> {
            weatherService.getWeatherFromBaidu(city);
        });
        return ResponseMessage.success();
    }


    //---------------------web页面接口 start-----------------------------------
    @GetMapping("/weatherList")
    public ModelAndView weatherList(Model model) {
        logger.info("WeatherController.weatherList()");
        List<WeatherModel> weatherModels = weatherService.getWeatherModels();
        model.addAttribute("weatherList", weatherModels);
        model.addAttribute("title", "天气预报管理");
//        return new ModelAndView("listWeather", "weatherModel", model);
        return new ModelAndView("weather/listWeather", "weatherModel", model);

    }

    // 查看天气明细信息
    @GetMapping("/weatherDetailList/{weatherId}")
    public ModelAndView weatherDetailList(@PathVariable(value = "weatherId", required = true) Long weatherId) {
        logger.info("WeatherController.weatherDetailList");
        Map map = new HashMap();
        map.put("weatherDetailList", weatherService.getWeatherDeatils(weatherId));
        map.put("title", "天气预报明细管理");
//        return new ModelAndView("listWeatherDetail", "weatherDetailModel", map);
        return new ModelAndView("weather/listWeatherDetail", "weatherDetailModel", map);
    }

    // 跳转到增加页面
    @GetMapping("/form")
    public ModelAndView createForm(Model model) {
        model.addAttribute("weather", new Weather());
        model.addAttribute("title", "添加天气预报");
//        return new ModelAndView("addWeather", "weatherModel", model);
        return new ModelAndView("weather/addWeather", "weatherModel", model);
    }

    // 根据城市名称增加天气预报
    @PostMapping("/addWeather")
    public ModelAndView addWeather(Weather weather) {
        weatherService.getWeatherFromBaidu(weather.getCity());
        return new ModelAndView("redirect:/weather/weatherList");
    }

    // 删除30天以前的天气预报和明细 2020-03-16
    @GetMapping("/delete")
    public ModelAndView delete() {
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DATE, -30);
        Date date = calender.getTime();
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.DATE_FORMAT_TYPE_2);
        String dateFormat = format.format(date);
        Date date30 = null;
        try {
            date30 = format.parse(dateFormat);
            logger.info("30天前是：{}", date30);
        } catch (Exception e) {
            logger.info("时间解析异常");
        }

        // 查询30天以前的所有weatherId
        List<Weather> weathers = weatherService.getWeather(date30);
        if (weathers != null && weathers.size() > 0) {
            Set<Long> idSet = weathers.stream().map(Weather::getWeatherId).collect(Collectors.toSet());
            Long[] ids = idSet.toArray(new Long[idSet.size()]);
            // 删除天气预报和明细
            weatherService.deleteWeatherByWeatherId(ids);
        }
        return new ModelAndView("redirect:/weather/weatherList");
    }
    //---------------------web页面接口 end-----------------------------------
}
