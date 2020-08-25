package com.study.business.people.service;

import com.study.business.people.entity.PeProvince;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author zhangpba
 * @date 2020-06-15
 * @descript 省
 */
public interface PeProvinceService {

    // 根据省编号查询
    public PeProvince findAllByProvinceCode(Integer provinceCode);

    // 根据省名称查询
    public PeProvince findAllByProvinceName(String provinceName);

    // 查询所有
    List<PeProvince> findAll();

    // 根据id查询单个
    PeProvince findById(Long id);

    // 保存一个
    void save(PeProvince province);

    // 修改
    void update(PeProvince province);

    // 删除一个
    void delete(Long id);

    // 分页
    Page<PeProvince> findAll(int page, int pageSize);


    // 初始化省province
    List<PeProvince> init();

}
