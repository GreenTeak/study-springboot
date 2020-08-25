package com.study.business.people.dao;

import com.study.business.people.entity.PeProvince;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author zhangpba
 * @date 2020-06-15
 * @descript 省
 */
public interface PeProvinceDao extends JpaRepository<PeProvince, Long>, JpaSpecificationExecutor<PeProvince> {

    // 根据省编号查询
    @Query(value = "select * from pe_province a where a.province_code = :provinceCode", nativeQuery = true)
    public PeProvince findByProvinceCode(Integer provinceCode);

    // 根据省名称查询
    @Query(value = "select * from pe_province a where a.province_name = :provinceName", nativeQuery = true)
    public PeProvince findByProvinceName(String provinceName);

    // 根据id查询
    @Query(value = "select * from pe_province a where a.id = :id", nativeQuery = true)
    public PeProvince findProvinceById(Long id);
}
