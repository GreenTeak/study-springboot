package com.study.business.people.dao;

import com.study.business.people.entity.PeCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zhangpba
 * @date 2020-06-15
 * @descript 省
 */
public interface PeCityDao extends JpaRepository<PeCity, Long>, JpaSpecificationExecutor<PeCity> {

    // 根据省编号查询
    @Query(value = "select * from pe_city a where a.province_and_city = :provinceAndCity", nativeQuery = true)
    List<PeCity> findByProvinceAndCity(Integer provinceAndCity);

    // 根据省编号查询
    @Query(value = "select * from pe_city a where a.province_name = :provinceName", nativeQuery = true)
    public PeCity findAllByProvinceName(String provinceName);

}
