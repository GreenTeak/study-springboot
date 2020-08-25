package com.study.business.people.service.impl;

import com.study.business.people.dao.PeProvinceDao;
import com.study.business.people.entity.PeProvince;
import com.study.business.people.service.PeProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangpba
 * @date 2020-06-15
 * @descript 省
 */
@Transactional
@Service
public class PeProvinceServiceImpl implements PeProvinceService {
    private static final Logger logger = LoggerFactory.getLogger(PeProvinceService.class);

    @Autowired
    private PeProvinceDao provinceDao;

    @Override
    public PeProvince findAllByProvinceCode(Integer provinceCode) {
        return provinceDao.findByProvinceCode(provinceCode);
    }

    @Override
    public PeProvince findAllByProvinceName(String provinceName) {
        return provinceDao.findByProvinceName(provinceName);
    }

    @Override
    public List<PeProvince> findAll() {
        return provinceDao.findAll();
    }

    @Override
    public PeProvince findById(Long id) {
        return provinceDao.findProvinceById(id);
    }

    @Override
    public void save(PeProvince province) {
        provinceDao.save(province);
    }

    @Override
    public void update(PeProvince province) {
        provinceDao.save(province);
    }

    @Override
    public void delete(Long id) {
        provinceDao.deleteById(id);
    }

    /**
     * 分页操作
     *
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page<PeProvince> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return (Page<PeProvince>) provinceDao.findAll(pageable);
    }


    @Override
    public List<PeProvince> init() {
        String provinces = "11 北京市\n" +
                "12 天津市\n" +
                "13 河北省\n" +
                "14 山西省\n" +
                "15 内蒙古自治区\n" +
                "21 辽宁省\n" +
                "22 吉林省\n" +
                "23 黑龙江省\n" +
                "31 上海市\n" +
                "32 江苏省\n" +
                "33 浙江省\n" +
                "34 安徽省\n" +
                "35 福建省\n" +
                "36 江西省\n" +
                "37 山东省\n" +
                "41 河南省\n" +
                "42 湖北省\n" +
                "43 湖南省\n" +
                "44 广东省\n" +
                "45 广西壮族自治区\n" +
                "46 海南省\n" +
                "50 重庆市\n" +
                "51 四川省\n" +
                "52 贵州省\n" +
                "53 云南省\n" +
                "54 西藏自治区\n" +
                "61 陕西省\n" +
                "62 甘肃省\n" +
                "63 青海省\n" +
                "64 宁夏回族自治区\n" +
                "65 新疆维吾尔自治区\n" +
                "71 台湾省\n" +
                "81 香港特别行政区\n" +
                "82 澳门特别行政区";

        List<PeProvince> provincesList = new ArrayList<>();

        String[] keyVales = provinces.split("\n");
        for (String keyValue : keyVales) {
            PeProvince province = new PeProvince();
            String[] provinceStr = keyValue.split(" ");
            String provinceName = provinceStr[1];
            Integer provinceCode = Integer.parseInt(provinceStr[0]);

            province.setProvinceCode(provinceCode);
            province.setProvinceName(provinceName);
            provincesList.add(province);
        }

        provinceDao.saveAll(provincesList);

        return provincesList;
    }
}
