package com.study.business.financial.service.impl;

import com.study.business.financial.ScheduleEnum;
import com.study.business.financial.model.ScheduleDetail;
import com.study.business.financial.model.ScheduleInfo;
import com.study.business.financial.service.IScheduleService;
import com.study.common.util.BigDecamalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.study.common.util.BigDecamalUtil.BIGDECAMAL_100;
import static com.study.common.util.BigDecamalUtil.DECIMAL_SCALE;

/**
 * 等额本金
 *
 * @date 2020-12-18
 */
@Service
public class ScheduleCaitalImpl implements IScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleInterestImpl.class);

    @Override
    public List<ScheduleDetail> createScheduleDetails(ScheduleInfo scheduleInfo) {

        List<ScheduleDetail> scheduleDetails = new ArrayList<>();
        try {
            BigDecimal yearRate = scheduleInfo.getYearRate();
            String startDate = scheduleInfo.getStartDate();
            int sumTerm = scheduleInfo.getSumTerm();
            BigDecimal total = scheduleInfo.getTotal();

            BigDecimal monthRate = yearRate.divide(BigDecamalUtil.BIGDECAMAL_12, DECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);
            //还款总利息
            BigDecimal sumInterest = new BigDecimal(sumTerm + 1).multiply(total).multiply(monthRate).divide(new BigDecimal(2), 2, BigDecimal.ROUND_HALF_UP);
            BigDecimal monthPrincipal = total.divide(new BigDecimal(sumTerm), DECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);
            BigDecimal remainTotal = total;    //剩余本金付初始值为总本金

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            Date date = format.parse(startDate);
            calendar.setTime(date);

            System.out.println("本金：" + total.setScale(2) + "\t年利率：" + yearRate.multiply(BIGDECAMAL_100) + "%\t总期数：" + sumTerm + "\t总利息:" + sumInterest);
            System.out.println("期次\t回款本息（元）\t回款利息（元）\t回款本金（元）\t剩余本金（元）\t回款日期");
            for (int i = 1; i <= sumTerm; i++) {

                //每月利息=（总本金-累计已还本金）×月利率
                BigDecimal interest = remainTotal.multiply(monthRate).setScale(2, BigDecimal.ROUND_HALF_UP);

                //月还本息
                BigDecimal monthPayTotal = monthPrincipal.add(interest);

                remainTotal = remainTotal.subtract(monthPrincipal);
                calendar.add(Calendar.MONTH, 1);
                System.out.println(i + "\t" + monthPayTotal.setScale(2, BigDecimal.ROUND_HALF_UP) + "\t\t" + interest.setScale(2, BigDecimal.ROUND_HALF_UP) + "\t\t" + monthPrincipal.setScale(2, BigDecimal.ROUND_HALF_UP) + "\t\t" + remainTotal.setScale(2, BigDecimal.ROUND_HALF_UP) + "\t\t" + format.format(calendar.getTime()));

                // 还款明细
                ScheduleDetail scheduleDetail = new ScheduleDetail();
                scheduleDetail.setPeriod(i);// 期次
                scheduleDetail.setDate(calendar.getTime()); // 时间
                scheduleDetail.setBalance(remainTotal.setScale(2, BigDecimal.ROUND_HALF_UP)); // 剩余本金
                scheduleDetail.setRePay(monthPayTotal.setScale(2, BigDecimal.ROUND_HALF_UP)); // 应还本息
                scheduleDetail.setRePri(monthPrincipal.setScale(2, BigDecimal.ROUND_HALF_UP)); // 应还本金
                scheduleDetail.setReInt(interest.setScale(2, BigDecimal.ROUND_HALF_UP)); // 应还利息
                scheduleDetails.add(scheduleDetail);

            }
        } catch (Exception e) {
            logger.info("等额本金明细生成异常：{} {}", e.getMessage());
            e.printStackTrace();
        }

        return scheduleDetails;
    }

    @Override
    public ScheduleEnum getScheduleModel() {
        return ScheduleEnum.CAITAL;
    }
}
