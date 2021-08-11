package com.study.business.financial.service.impl;

import com.study.business.financial.ScheduleEnum;
import com.study.business.financial.mapper.ScheduleDetailMapper;
import com.study.business.financial.model.ScheduleDetail;
import com.study.business.financial.model.ScheduleInfo;
import com.study.business.financial.service.IScheduleService;
import com.study.common.util.BigDecamalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 等额本息
 *
 * @date 2020-12-18
 */
@Service
public class ScheduleInterestImpl implements IScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleInterestImpl.class);

    @Autowired
    private ScheduleDetailMapper scheduleDetailMapper;

    @Override
    public List<ScheduleDetail> createScheduleDetails(ScheduleInfo scheduleInfo) {

        List<ScheduleDetail> scheduleDetails = new ArrayList<>();
        try {
            BigDecimal yearRate = scheduleInfo.getYearRate();
            String startDate = scheduleInfo.getStartDate();
            int sumTerm = scheduleInfo.getSumTerm();
            BigDecimal total = scheduleInfo.getTotal();

            BigDecimal monthRate = yearRate.divide(BigDecamalUtil.BIGDECAMAL_12, BigDecamalUtil.DECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);

            // 每月还款额=[总本金×月利率×(1+月利率)^还款月数]÷[(1+月利率)^总期数-1]
            BigDecimal tmp = monthRate.add(new BigDecimal(1)).pow(sumTerm);//(1+月利率)^还款月数
            BigDecimal monthPayTotal = total
                    .multiply(monthRate)
                    .multiply(tmp)
                    .divide(tmp.subtract(new BigDecimal(1)), 2, BigDecimal.ROUND_HALF_UP);

            // 总利息=月还本息×总期数-总本金
            BigDecimal sumInterest = monthPayTotal.multiply(new BigDecimal(sumTerm)).subtract(total);


            BigDecimal remainTotal = total;    //剩余本金付初始值为总本金
            BigDecimal monthPrincipal = BigDecimal.ZERO;

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            Date date = format.parse(startDate);
            calendar.setTime(date);

            System.out.println("本金：" + total.setScale(2) + "\t年利率：" + yearRate.multiply(BigDecamalUtil.BIGDECAMAL_100) + "%\t总期数：" + sumTerm + "\t总利息:" + sumInterest);
            System.out.println("期次\t回款本息（元）\t回款利息（元）\t回款本金（元）\t剩余本金（元）\t回款日期");
            for (int i = 1; i <= sumTerm; i++) {

                // 月还利息=剩余本金×月利率
                BigDecimal interest = remainTotal.multiply(monthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
                //为避免因精度损失产生误差，最后一期  还款利息=月还本息-剩余本金
                //为避免利息产生负数的情况出现 当利息小于等于0时利息赋值为0.1元
                if (sumTerm == i) {
                    interest = monthPayTotal.subtract(remainTotal);
                    if (interest.compareTo(BigDecimal.ZERO) <= 0) {
                        interest = new BigDecimal(1).divide(new BigDecimal(10), 2, BigDecimal.ROUND_HALF_UP);
                    }
                }
                //月还本金=月还本息-月还利息
                monthPrincipal = monthPayTotal.subtract(interest);

                remainTotal = remainTotal.subtract(monthPrincipal);
                calendar.add(Calendar.MONTH, 1);
                System.out.println(i + "\t" + monthPayTotal + "\t\t" + interest + "\t\t" + monthPrincipal + "\t\t" + remainTotal + "\t\t" + format.format(calendar.getTime()));

                // 还款明细
                ScheduleDetail scheduleDetail = new ScheduleDetail();
                scheduleDetail.setBatchNo(i);// 期次
                scheduleDetail.setRepayDate(calendar.getTime()); // 时间
                scheduleDetail.setSurPriAmt(remainTotal.setScale(2, BigDecimal.ROUND_HALF_UP)); // 剩余本金
                scheduleDetail.setRepayPayAmt(monthPayTotal.setScale(2, BigDecimal.ROUND_HALF_UP)); // 应还本息
                scheduleDetail.setRepayPriAmt(monthPrincipal.setScale(2, BigDecimal.ROUND_HALF_UP)); // 应还本金
                scheduleDetail.setRepayIntAmt(interest.setScale(2, BigDecimal.ROUND_HALF_UP)); // 应还利息
                scheduleDetail.setCreateDate(new Date());
                scheduleDetails.add(scheduleDetail);

                scheduleDetailMapper.addScheduleDetail(scheduleDetail);
            }

        } catch (Exception e) {
            logger.info("等额本息明细生成异常：{} {}", e.getMessage());
            e.printStackTrace();
        }

        return scheduleDetails;
    }

    @Override
    public ScheduleEnum getScheduleModel() {
        return ScheduleEnum.INTEREST;
    }
}
