package com.study.business.people.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @author zhangpba
 * @date 2020-06-15
 * @descript 省
 */
@Entity
@Table(name = "pe_province")
@Component
public class PeProvince {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    // 省市代码
    private Integer provinceCode;
    // 省市名称
    private String provinceName;

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
}
