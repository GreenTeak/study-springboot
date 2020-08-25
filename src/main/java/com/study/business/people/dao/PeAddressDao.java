package com.study.business.people.dao;

import com.study.business.people.entity.PeAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zhangpba
 * @date 2020-06-16
 * @descript 省
 */
public interface PeAddressDao extends JpaRepository<PeAddress, Long>, JpaSpecificationExecutor<PeAddress> {

    // 根据省编号查询
    @Query(value = "select * from pe_address a where a.id = :id", nativeQuery = true)
    List<PeAddress> findPeAddressById(Integer id);

}
