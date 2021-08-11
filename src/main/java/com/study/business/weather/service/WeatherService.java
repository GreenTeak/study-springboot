package com.study.business.weather.service;

import com.study.business.weather.entity.Weather;
import com.study.business.weather.entity.WeatherDetail;
import com.study.business.weather.model.WeatherModel;

import java.util.Date;
import java.util.List;

public interface WeatherService {

    // 在数据库中查询天气预报
    List<WeatherDetail> getWeatherByCity(String city);

    // 插入天气信息和天气明细信息
    void insertWeather(WeatherModel weatherModel);

    // 解析天气返回报文
    void parseWeather(String result);

    // 关联查询天气明细
    List<WeatherModel> getWeatherModels();

    // 根据城市查询天气明细
    List<WeatherDetail> getWeatherDeatils(Long weatheId);

    // 从百度获取一个城市的天气预报
    void getWeatherFromBaidu(String cityName);

    // 根据id删除Weather和明细
    void deleteWeatherByWeatherId(Long[] ids);

    // 查询30天以前的所有weather
    List<Weather> getWeather(Date date);
}
