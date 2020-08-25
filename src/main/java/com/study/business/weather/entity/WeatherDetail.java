package com.study.business.weather.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 天气预报明细
 */
@Entity
public class WeatherDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 设置自增
    private Long weatherDetailId;
    // 天气预报id
    private Long weatherId;
    // 原始日期（报文中解析出来的日期）
    private String date;
    // 风力
    private String fengli;
    // 风向
    private String fengxiang;
    // 最高温
    private String high;
    // 最低温
    private String low;
    // 天气类型
    private String type;
    // 星期
    private String week;
    // 时间戳
    private Date timestamp;

    public Long getWeatherDetailId() {
        return weatherDetailId;
    }

    public void setWeatherDetailId(Long weatherDetailId) {
        this.weatherDetailId = weatherDetailId;
    }

    public Long getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Long weatherId) {
        this.weatherId = weatherId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
