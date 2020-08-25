package com.study.business.people.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @author zhangpba
 * @date 2020-06-16
 * @descript 地址
 */
@Entity
@Table(name = "pe_address")
@Component
public class PeAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // 省代码
    private Long provinceCode;

    // 市代码
    private Long cityCode;

    // 详细地址
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Long provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Long getCityCode() {
        return cityCode;
    }

    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
