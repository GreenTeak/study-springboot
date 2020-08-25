package com.study.business.people.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @author zhangpba
 * @date 2020-06-15
 * @descript 省
 */
@Entity
@Table(name = "pe_city")
@Component
public class PeCity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    // 省代码
    private Integer provinceCode;

    // 省名称
    private String provinceName;

    // 市代码
    private Integer cityCode;

    // 市名称
    private String cityName;

    // 省市代码
    private Integer provinceAndCity;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getProvinceAndCity() {
        return provinceAndCity;
    }

    public void setProvinceAndCity(Integer provinceAndCity) {
        this.provinceAndCity = provinceAndCity;
    }
}
