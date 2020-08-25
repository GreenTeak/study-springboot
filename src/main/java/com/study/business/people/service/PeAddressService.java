package com.study.business.people.service;

import com.study.business.people.entity.PeAddress;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author zhangpba
 * @date 2020-06-16
 * @descript 地址
 */
public interface PeAddressService {

    // 根据id查询
    List<PeAddress> findPeAddressById(Integer id);

    // 查询所有
    List<PeAddress> findAll();

    // 保存一个
    void save(PeAddress address);

    // 修改
    void update(PeAddress address);

    // 删除一个
    void delete(Long id);

    // 分页
    Page<PeAddress> findAll(int page, int pageSize);
}
