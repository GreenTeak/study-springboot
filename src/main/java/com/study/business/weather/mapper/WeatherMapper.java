package com.study.business.weather.mapper;

import com.study.business.weather.entity.Weather;
import com.study.business.weather.entity.WeatherDetail;
import com.study.business.weather.model.WeatherModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

//@Mapper
public interface WeatherMapper {
    void addWeather(Weather weather);

    List<WeatherModel> getWeatherModels();

    boolean deleteByWeatherId(Long[] weatherIds);

    List<Weather> getWeather(Date date);
}
