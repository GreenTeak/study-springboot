package com.study.business.financial.mapper;

import com.study.business.financial.model.ScheduleDetail;

/**
 * 还款计划明细
 *
 * @date 2021-08-12
 */
public interface ScheduleDetailMapper {

    /**
     * 插入还款计划明细
     *
     * @param scheduleDetail
     * @date 2021-08-11
     */
    void addScheduleDetail(ScheduleDetail scheduleDetail);
}
