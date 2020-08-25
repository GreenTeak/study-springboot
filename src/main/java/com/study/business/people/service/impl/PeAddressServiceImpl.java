package com.study.business.people.service.impl;

import com.study.business.people.dao.PeAddressDao;
import com.study.business.people.entity.PeAddress;
import com.study.business.people.entity.PeCity;
import com.study.business.people.service.PeAddressService;
import com.study.business.people.service.PeProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangpba
 * @date 2020-06-16
 * @descript 地址
 */
@Transactional
@Service
public class PeAddressServiceImpl implements PeAddressService {
    private static final Logger logger = LoggerFactory.getLogger(PeProvinceService.class);

    @Autowired
    private PeAddressDao addressDao;

    @Override
    public List<PeAddress> findPeAddressById(Integer id) {
        return addressDao.findPeAddressById(id);
    }

    @Override
    public List<PeAddress> findAll() {
        return addressDao.findAll();
    }

    @Override
    public void save(PeAddress address) {
        addressDao.save(address);
    }

    @Override
    public void update(PeAddress address) {
        addressDao.save(address);
    }

    @Override
    public void delete(Long id) {
        addressDao.deleteById(id);
    }

    @Override
    public Page<PeAddress> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return (Page<PeAddress>) addressDao.findAll(pageable);
    }
}
