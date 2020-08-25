package com.study.business.weather.model;

import com.study.business.weather.entity.WeatherDetail;

import java.util.List;

/**
 * 天气预报 业务模型
 */
public class WeatherModel {
    private Long weatherId;
    // 城市
    private String city;
    // 提示
    private String ganmao;
    // 温度
    private Integer wendu;
    // 时间
    private String createDate;
    // 天气明细
    private List<WeatherDetail> weatherDetails;

    public Long getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Long weatherId) {
        this.weatherId = weatherId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public Integer getWendu() {
        return wendu;
    }

    public void setWendu(Integer wendu) {
        this.wendu = wendu;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<WeatherDetail> getWeatherDetails() {
        return weatherDetails;
    }

    public void setWeatherDetails(List<WeatherDetail> weatherDetails) {
        this.weatherDetails = weatherDetails;
    }
}
