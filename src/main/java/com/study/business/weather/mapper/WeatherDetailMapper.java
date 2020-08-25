package com.study.business.weather.mapper;

import com.study.business.weather.entity.WeatherDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface WeatherDetailMapper {

    void addWeatherDetail(WeatherDetail weatherDetail);

    List<WeatherDetail> getWeatherDeatils(Long weatheId);

    boolean deleteByWeatherId(Long[] weatherIds);
}
