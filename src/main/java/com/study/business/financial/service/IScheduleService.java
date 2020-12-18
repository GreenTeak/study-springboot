package com.study.business.financial.service;

import com.study.business.financial.ScheduleEnum;
import com.study.business.financial.model.ScheduleDetail;
import com.study.business.financial.model.ScheduleInfo;

import java.util.List;

/**
 * 还款计划
 * 2020-12-18
 */
public interface IScheduleService {


    /**
     * 生成还款计划明细
     *
     * @param scheduleInfo
     * @return
     */
    List<ScheduleDetail> createScheduleDetails(ScheduleInfo scheduleInfo);

    /**
     * 获取模型
     * @return
     */
    ScheduleEnum getScheduleModel();
}
