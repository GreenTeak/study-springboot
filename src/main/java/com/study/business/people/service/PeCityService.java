package com.study.business.people.service;

import com.study.business.people.entity.PeCity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author zhangpba
 * @date 2020-06-15
 * @descript 市
 */
public interface PeCityService {

    // 根据省编号查询
    public List<PeCity> findByProvinceAndCity(Integer provinceAndCity);

    // 根据省名称查询
    PeCity findAllByProvinceName(String provinceName);

    // 查询所有
    List<PeCity> findAll();

    // 保存一个
    void save(PeCity city);

    // 修改
    void update(PeCity city);

    // 删除一个
    void delete(Long id);

    // 分页
    Page<PeCity> findAll(int page, int pageSize);

    // 初始化省province
    List<PeCity> init();

}
