package com.study.business.people.service.impl;

import com.study.business.people.dao.PeCityDao;
import com.study.business.people.entity.PeCity;
import com.study.business.people.service.PeCityService;
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
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangpba
 * @date 2020-06-15
 * @descript 省
 */
@Transactional
@Service
public class PeCityServiceImpl implements PeCityService {
    private static final Logger logger = LoggerFactory.getLogger(PeProvinceService.class);

    @Autowired
    private PeCityDao cityDao;

    @Override
    public List<PeCity> findByProvinceAndCity(Integer provinceAndCity) {
        return cityDao.findByProvinceAndCity(provinceAndCity);
    }

    @Override
    public PeCity findAllByProvinceName(String provinceName) {

        return null;
    }

    @Override
    public List<PeCity> findAll() {
        return cityDao.findAll();
    }


    @Override
    public void save(PeCity city) {
        cityDao.save(city);
    }

    @Override
    public void update(PeCity city) {
        cityDao.save(city);
    }

    @Override
    public void delete(Long id) {
        cityDao.deleteById(id);
    }

    @Override
    public Page<PeCity> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return (Page<PeCity>) cityDao.findAll(pageable);
    }

    @Override
    public List<PeCity> init() {

        String citys = "1100 北京 北京\n" +
                "1200 天津 天津\n" +
                "1301 河北 石家庄\n" +
                "1302 河北 唐山\n" +
                "1303 河北 秦皇岛\n" +
                "1304 河北 邯郸\n" +
                "1305 河北 邢台\n" +
                "1306 河北 保定\n" +
                "1307 河北 张家口\n" +
                "1308 河北 承德\n" +
                "1309 河北 沧州\n" +
                "1310 河北 廊坊\n" +
                "1311 河北 衡水\n" +
                "1401 山西 太原\n" +
                "1402 山西 大同\n" +
                "1403 山西 阳泉\n" +
                "1404 山西 长治\n" +
                "1405 山西 晋城\n" +
                "1406 山西 朔州\n" +
                "1407 山西 晋中\n" +
                "1408 山西 运城\n" +
                "1409 山西 忻州\n" +
                "1410 山西 临汾\n" +
                "1411 山西 吕梁\n" +
                "1424 山西 晋中\n" +
                "1501 内蒙古 呼和浩特\n" +
                "1502 内蒙古 包头\n" +
                "1503 内蒙古 乌海\n" +
                "1504 内蒙古 赤峰\n" +
                "1505 内蒙古 通辽\n" +
                "1506 内蒙古 鄂尔多斯\n" +
                "1507 内蒙古 呼伦贝尔\n" +
                "1508 内蒙古 巴彦淖尔\n" +
                "1509 内蒙古 乌兰察布\n" +
                "1522 内蒙古 兴安盟\n" +
                "1525 内蒙古 锡林郭勒\n" +
                "1529 内蒙古 阿拉善盟\n" +
                "2101 辽宁 沈阳\n" +
                "2102 辽宁 大连\n" +
                "2103 辽宁 鞍山\n" +
                "2104 辽宁 抚顺\n" +
                "2105 辽宁 本溪\n" +
                "2106 辽宁 丹东\n" +
                "2107 辽宁 锦州\n" +
                "2108 辽宁 营口\n" +
                "2109 辽宁 阜新\n" +
                "2110 辽宁 辽阳\n" +
                "2111 辽宁 盘锦\n" +
                "2112 辽宁 铁岭\n" +
                "2113 辽宁 朝阳\n" +
                "2114 辽宁 葫芦岛\n" +
                "2200 吉林 吉林\n" +
                "2201 吉林 长春\n" +
                "2202 吉林 吉林\n" +
                "2203 吉林 四平\n" +
                "2204 吉林 辽源\n" +
                "2205 吉林 通化\n" +
                "2206 吉林 白山\n" +
                "2207 吉林 松原\n" +
                "2208 吉林 白城\n" +
                "2224 吉林 延边州\n" +
                "2301 黑龙江 哈尔滨\n" +
                "2302 黑龙江 齐齐哈尔\n" +
                "2303 黑龙江 鸡西\n" +
                "2304 黑龙江 鹤岗\n" +
                "2305 黑龙江 双鸭山\n" +
                "2306 黑龙江 大庆\n" +
                "2307 黑龙江 伊春\n" +
                "2308 黑龙江 佳木斯\n" +
                "2309 黑龙江 七台河\n" +
                "2310 黑龙江 牡丹江\n" +
                "2311 黑龙江 黑河\n" +
                "2312 黑龙江 绥化\n" +
                "2321 黑龙江 哈尔滨\n" +
                "2327 黑龙江 大兴安岭\n" +
                "3100 上海 上海\n" +
                "3201 江苏 南京\n" +
                "3202 江苏 无锡\n" +
                "3203 江苏 徐州\n" +
                "3204 江苏 常州\n" +
                "3205 江苏 苏州\n" +
                "3206 江苏 南通\n" +
                "3207 江苏 连云港\n" +
                "3208 江苏 淮安\n" +
                "3209 江苏 盐城\n" +
                "3210 江苏 扬州\n" +
                "3211 江苏 镇江\n" +
                "3212 江苏 泰州\n" +
                "3301 浙江省 杭州\n" +
                "3302 浙江省 宁波\n" +
                "3303 浙江省 温州\n" +
                "3304 浙江省 嘉兴\n" +
                "3305 浙江省 湖州\n" +
                "3306 浙江省 绍兴\n" +
                "3307 浙江省 金华\n" +
                "3308 浙江省 衢州\n" +
                "3309 浙江省 舟山\n" +
                "3310 浙江省 台州\n" +
                "3311 浙江省 丽水\n" +
                "3401 安徽 合肥\n" +
                "3402 安徽 芜湖\n" +
                "3403 安徽 蚌埠\n" +
                "3404 安徽 淮南\n" +
                "3405 安徽 马鞍山\n" +
                "3406 安徽 淮北\n" +
                "3407 安徽 铜陵\n" +
                "3408 安徽 安庆\n" +
                "3410 安徽 黄山\n" +
                "3411 安徽 滁州\n" +
                "3412 安徽 阜阳\n" +
                "3413 安徽 宿州\n" +
                "3415 安徽 六安\n" +
                "3416 安徽 亳州\n" +
                "3417 安徽 池州\n" +
                "3418 安徽 宣城\n" +
                "3422 安徽 宿县\n" +
                "3501 福建 福州\n" +
                "3502 福建 厦门\n" +
                "3503 福建 莆田\n" +
                "3504 福建 三明\n" +
                "3505 福建 泉州\n" +
                "3506 福建 漳州\n" +
                "3507 福建 南平\n" +
                "3508 福建 龙岩\n" +
                "3509 福建 宁德\n" +
                "3601 江西 南昌\n" +
                "3602 江西 景德镇\n" +
                "3603 江西 萍乡\n" +
                "3604 江西 九江\n" +
                "3605 江西 新余\n" +
                "3606 江西 鹰潭\n" +
                "3607 江西 赣州\n" +
                "3608 江西 吉安\n" +
                "3609 江西 宜春\n" +
                "3610 江西 抚州\n" +
                "3611 江西 上饶\n" +
                "3701 山东 济南\n" +
                "3702 山东 青岛\n" +
                "3703 山东 淄博\n" +
                "3704 山东 枣庄\n" +
                "3705 山东 东营\n" +
                "3706 山东 烟台\n" +
                "3707 山东 潍坊\n" +
                "3708 山东 济宁\n" +
                "3709 山东 泰安\n" +
                "3710 山东 威海\n" +
                "3711 山东 日照\n" +
                "3712 山东 莱芜\n" +
                "3713 山东 临沂\n" +
                "3714 山东 德州\n" +
                "3715 山东 聊城\n" +
                "3716 山东 滨州\n" +
                "3717 山东 菏泽\n" +
                "4101 河南 郑州\n" +
                "4102 河南 开封\n" +
                "4103 河南 洛阳\n" +
                "4104 河南 平顶山\n" +
                "4105 河南 安阳\n" +
                "4106 河南 鹤壁\n" +
                "4107 河南 新乡\n" +
                "4108 河南 焦作\n" +
                "4109 河南 濮阳\n" +
                "4110 河南 许昌\n" +
                "4111 河南 漯河\n" +
                "4112 河南 三门峡\n" +
                "4113 河南 南阳\n" +
                "4114 河南 商丘\n" +
                "4115 河南 信阳\n" +
                "4116 河南 周口\n" +
                "4117 河南 驻马店\n" +
                "4201 湖北 武汉\n" +
                "4202 湖北 黄石\n" +
                "4203 湖北 十堰\n" +
                "4205 湖北 宜昌\n" +
                "4206 湖北 襄阳\n" +
                "4207 湖北 鄂州\n" +
                "4208 湖北 荆门\n" +
                "4209 湖北 孝感\n" +
                "4210 湖北 荆州\n" +
                "4211 湖北 黄冈\n" +
                "4212 湖北 咸宁\n" +
                "4213 湖北 随州\n" +
                "4228 湖北 恩施州\n" +
                "4301 湖南 长沙\n" +
                "4302 湖南 株洲\n" +
                "4303 湖南 湘潭\n" +
                "4304 湖南 衡阳\n" +
                "4305 湖南 邵阳\n" +
                "4306 湖南 岳阳\n" +
                "4307 湖南 常德\n" +
                "4309 湖南 益阳\n" +
                "4310 湖南 郴州\n" +
                "4311 湖南 永州\n" +
                "4312 湖南 怀化\n" +
                "4313 湖南 娄底\n" +
                "4331 湖南 湘西州\n" +
                "4401 广东 广州\n" +
                "4402 广东 韶关\n" +
                "4403 广东 深圳\n" +
                "4404 广东 珠海\n" +
                "4405 广东 汕头\n" +
                "4406 广东 佛山\n" +
                "4407 广东 江门\n" +
                "4408 广东 湛江\n" +
                "4409 广东 茂名\n" +
                "4412 广东 肇庆\n" +
                "4413 广东 惠州\n" +
                "4414 广东 梅州\n" +
                "4415 广东 汕尾\n" +
                "4416 广东 河源\n" +
                "4417 广东 阳江\n" +
                "4418 广东 清远\n" +
                "4419 广东 东莞\n" +
                "4420 广东 中山\n" +
                "4451 广东 潮州\n" +
                "4452 广东 揭阳\n" +
                "4453 广东 云浮\n" +
                "4501 广西 南宁\n" +
                "4502 广西 柳州\n" +
                "4503 广西 桂林\n" +
                "4504 广西 梧州\n" +
                "4505 广西 北海\n" +
                "4506 广西 防城港\n" +
                "4507 广西 钦州\n" +
                "4508 广西 贵港\n" +
                "4509 广西 玉林\n" +
                "4510 广西 百色\n" +
                "4511 广西 贺州\n" +
                "4512 广西 河池\n" +
                "4513 广西 来宾\n" +
                "4514 广西 崇左\n" +
                "4524 广西 贺州\n" +
                "4601 海南 海口\n" +
                "4602 海南 三亚\n" +
                "4690 海南 儋州\n" +
                "4690 海南 三沙\n" +
                "5000 重庆 重庆\n" +
                "5101 四川 成都\n" +
                "5103 四川 自贡\n" +
                "5104 四川 攀枝花\n" +
                "5105 四川 泸州\n" +
                "5106 四川 德阳\n" +
                "5107 四川 绵阳\n" +
                "5108 四川 广元\n" +
                "5109 四川 遂宁\n" +
                "5110 四川 内江\n" +
                "5111 四川 乐山\n" +
                "5113 四川 南充\n" +
                "5114 四川 眉山\n" +
                "5115 四川 宜宾\n" +
                "5116 四川 广安\n" +
                "5117 四川 达州\n" +
                "5119 四川 巴中\n" +
                "5120 四川 资阳\n" +
                "5134 四川 凉山州\n" +
                "5201 贵州 贵阳\n" +
                "5202 贵州 六盘水\n" +
                "5203 贵州 遵义\n" +
                "5204 贵州 安顺\n" +
                "5205 贵州 毕节\n" +
                "5206 贵州 铜仁\n" +
                "5223 贵州 黔西南州\n" +
                "5226 贵州 黔东南州\n" +
                "5227 贵州 黔南州\n" +
                "5301 云南 昆明\n" +
                "5303 云南 曲靖\n" +
                "5304 云南 玉溪\n" +
                "5305 云南 保山\n" +
                "5306 云南 昭通\n" +
                "5307 云南 丽江\n" +
                "5308 云南 普洱\n" +
                "5309 云南 临沧\n" +
                "5323 云南 楚雄州\n" +
                "5325 云南 红河州\n" +
                "5326 云南 文山州\n" +
                "5328 云南 西双版纳\n" +
                "5329 云南 大理州\n" +
                "5331 云南 德宏州\n" +
                "5333 云南 怒江州\n" +
                "5334 云南 迪庆州\n" +
                "5401 西藏 拉萨\n" +
                "5421 西藏 昌都\n" +
                "5422 西藏 山南\n" +
                "5423 西藏 日喀则\n" +
                "5426 西藏 林芝\n" +
                "6101 陕西 西安\n" +
                "6102 陕西 铜川\n" +
                "6103 陕西 宝鸡\n" +
                "6104 陕西 咸阳\n" +
                "6105 陕西 渭南\n" +
                "6106 陕西 延安\n" +
                "6107 陕西 汉中\n" +
                "6108 陕西 榆林\n" +
                "6109 陕西 安康\n" +
                "6110 陕西 商洛\n" +
                "6201 甘肃省 兰州\n" +
                "6202 甘肃省 嘉峪关\n" +
                "6203 甘肃省 金昌\n" +
                "6204 甘肃省 白银\n" +
                "6205 甘肃省 天水\n" +
                "6206 甘肃省 武威\n" +
                "6207 甘肃省 张掖\n" +
                "6208 甘肃省 平凉\n" +
                "6209 甘肃省 酒泉\n" +
                "6210 甘肃省 庆阳\n" +
                "6211 甘肃省 定西\n" +
                "6212 甘肃省 陇南\n" +
                "6229 甘肃省 临夏州\n" +
                "6230 甘肃省 甘南州\n" +
                "6301 青海 西宁\n" +
                "6321 青海 海东\n" +
                "6322 青海 海北州\n" +
                "6323 青海 黄南州\n" +
                "6325 青海 海南州\n" +
                "6326 青海 果洛州\n" +
                "6327 青海 玉树州\n" +
                "6328 青海 海西州\n" +
                "6401 宁夏 银川\n" +
                "6402 宁夏 石嘴山\n" +
                "6403 宁夏 吴忠\n" +
                "6404 宁夏 固原\n" +
                "6405 宁夏 中卫\n" +
                "6501 新疆 乌鲁木齐\n" +
                "6502 新疆 克拉玛依\n" +
                "6521 新疆 吐鲁番\n" +
                "6522 新疆 哈密\n" +
                "6523 新疆 昌吉州\n" +
                "6527 新疆 博尔塔拉州\n" +
                "6528 新疆 巴音郭楞\n" +
                "6530 新疆 克孜勒苏州\n" +
                "6531 新疆 喀什地区\n" +
                "6532 新疆 和田地区\n" +
                "6540 新疆 伊犁州\n" +
                "6542 新疆 塔城地区\n" +
                "6590 新疆 石河子";

        List<PeCity> cities = new ArrayList<>();
        String[] cityArrays = citys.split("\n");
        Arrays.asList(cityArrays).forEach(cityArray -> {

            PeCity city = new PeCity();
            String[] c = cityArray.split(" ");
            String code = c[0];
            String provinceName = c[1];
            String cityName = c[2];

            city.setCityCode(Integer.parseInt(code.substring(2, 4)));
            city.setProvinceCode(Integer.parseInt(code.substring(0, 2)));
            city.setCityName(cityName);
            city.setProvinceName(provinceName);
            Integer provinceAndCity = Integer.parseInt(code);
            city.setProvinceAndCity(provinceAndCity);

            // 如果市数据已经存在，不在进行增加
            List<PeCity> peCitys = cityDao.findByProvinceAndCity(provinceAndCity);
            if (peCitys == null || peCitys.isEmpty()) {
                cities.add(city);
            }

        });

        cityDao.saveAll(cities);
        return cities;
    }
}
