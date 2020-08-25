package com.study.business.weather.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.common.constant.ComConstant;
import com.study.business.weather.entity.Weather;
import com.study.business.weather.entity.WeatherDetail;
import com.study.business.weather.mapper.WeatherDetailMapper;
import com.study.business.weather.mapper.WeatherMapper;
import com.study.business.weather.model.WeatherModel;
import com.study.business.weather.service.WeatherService;
import com.study.common.util.DateUtil;
import com.study.common.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class WeatherServiceImpl implements WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private WeatherMapper weatherMapper;
    @Autowired
    private WeatherDetailMapper weatherDetailMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<WeatherDetail> getWeatherByCity(String city) {
        return null;
    }

    /**
     * 天气预报入库
     *
     * @param weatherModel
     */
    @Override
    public void insertWeather(WeatherModel weatherModel) {
        Weather weather = new Weather();
        String idStr = IdUtil.getNextID(Calendar.getInstance().getTimeInMillis() + "");
        logger.info("生成的id={}", idStr);
        Long id = Long.parseLong(idStr);
        weather.setWeatherId(id);
        weather.setCity(weatherModel.getCity());
        weather.setGanmao(weatherModel.getGanmao());
        weather.setWendu(weatherModel.getWendu());
        weather.setCreateDate(new Date());
        weatherMapper.addWeather(weather);

        List<WeatherDetail> weatherDetails = weatherModel.getWeatherDetails();
        weatherDetails.forEach(weatherDetail -> {
            weatherDetail.setWeatherId(id);
            weatherDetailMapper.addWeatherDetail(weatherDetail);
        });
    }

    /**
     * 解析天气预报
     *
     * @param result
     */
    public void parseWeather(String result) {
        JSONObject jsonObject = JSONObject.parseObject(result);
        String status = jsonObject.getString("status");
        if ("1000".equals(status)) {
            JSONObject data = jsonObject.getJSONObject("data");
            String city = data.getString("city");
            String ganmao = data.getString("ganmao");
            Integer wendu = data.getInteger("wendu");

            JSONArray forecast = data.getJSONArray("forecast");
            JSONObject yesterday = data.getJSONObject("yesterday");

            // 获取地区信息
            WeatherModel weatherModel = new WeatherModel();
            weatherModel.setCity(city);
            weatherModel.setGanmao(ganmao);
            weatherModel.setWendu(wendu);
            SimpleDateFormat format = new SimpleDateFormat(DateUtil.DATE_FORMAT_TYPE_3);
            String createDate = format.format(new Date());
            weatherModel.setCreateDate(createDate);

            // 获取未来5天的明细数据
            List<WeatherDetail> weatherDetails = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i < forecast.size(); i++) {
                calendar.add(Calendar.DATE, i);
                Date timestamp = calendar.getTime();
                logger.info("timestamp:{}", timestamp);
                calendar.add(Calendar.DATE, -i);
                JSONObject weatherJson = (JSONObject) forecast.get(i);
                WeatherDetail weatherDetail = buildWeatherDetail(weatherJson, timestamp);
                weatherDetails.add(weatherDetail);
            }

            // 获取昨天的明细数据
            calendar.add(Calendar.DATE, -1);
            Date date = calendar.getTime();
            WeatherDetail weatherDetail = buildWeatherDetail(yesterday, date);
            weatherDetails.add(weatherDetail);
            weatherModel.setWeatherDetails(weatherDetails);

            this.insertWeather(weatherModel);
        }
    }

    @Override
    public List<WeatherModel> getWeatherModels() {
        return weatherMapper.getWeatherModels();
    }

    // 根据天气预报id查询天气明细
    @Override
    public List<WeatherDetail> getWeatherDeatils(Long weatheId) {
        return weatherDetailMapper.getWeatherDeatils(weatheId);
    }

    /**
     * 构造天气详情
     *
     * @param jsonObject
     * @return
     */
    public WeatherDetail buildWeatherDetail(JSONObject jsonObject, Date timestamp) {
        String fengli = jsonObject.getString("fengli");
        String fengxiang = jsonObject.getString("fengxiang");
        String date = jsonObject.getString("date");
        String high = jsonObject.getString("high");
        String low = jsonObject.getString("low");
        String type = jsonObject.getString("type");
        // yesterday的风力和风向字段与未来五天的key值不一致
        if (fengli == null || "".equals(fengli)) {
            fengli = jsonObject.getString("fl");
        }
        if (fengxiang == null || "".equals(fengxiang)) {
            fengxiang = jsonObject.getString("fx");
        }
        WeatherDetail weatherDetail = new WeatherDetail();
        weatherDetail.setTimestamp(timestamp);
        weatherDetail.setDate(date);
        weatherDetail.setFengli(fengli);
        weatherDetail.setFengxiang(fengxiang);
        weatherDetail.setHigh(high);
        weatherDetail.setLow(low);
        weatherDetail.setType(type);
        String[] dateArry = date.split("日");
        String week = dateArry[1];
        weatherDetail.setWeek(week);
        return weatherDetail;
    }


    // 从百度获取一个城市的天气预报
    @Override
    public void getWeatherFromBaidu(String city) {
        String apiurl = ComConstant.baudi_Weather_Api + city;
        logger.info(apiurl);
        String result = null;
        try {
            result = restTemplate.getForObject(apiurl, String.class);
            logger.info("请求天气预报返回报文：{}", result);
        } catch (Exception e) {
            logger.info("请求天气预报结果异常，{}", e.getMessage());
        }
        if (result != null) {
            // 解析
            parseWeather(result);
        }
    }

    // 根据id删除Weather和明细
    @Override
    public void deleteWeatherByWeatherId(Long[] ids) {
        weatherMapper.deleteByWeatherId(ids);
        weatherDetailMapper.deleteByWeatherId(ids);
    }

    // 查询30天以前的所有weather
    @Override
    public List<Weather> getWeather(Date date){
        return weatherMapper.getWeather(date);
    }
}
