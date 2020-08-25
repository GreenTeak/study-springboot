package com.study.business.weather.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 城市天气预报表
 */
@Entity
public class Weather {
    @Id
    @GeneratedValue
    private Long weatherId;
    // 城市
    private String city;
    // 提示
    private String ganmao;
    // 温度
    private Integer wendu;
    // 时间
    private Date createDate;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
