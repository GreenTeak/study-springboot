package com.study.business.financial.model;

import java.util.List;

public class ScheduleOut {

    private ScheduleInfo scheduleInfo;

    // 还款计划明细
    private List<ScheduleDetail> scheduleDetails;

    public ScheduleInfo getScheduleInfo() {
        return scheduleInfo;
    }

    public void setScheduleInfo(ScheduleInfo scheduleInfo) {
        this.scheduleInfo = scheduleInfo;
    }

    public List<ScheduleDetail> getScheduleDetails() {
        return scheduleDetails;
    }

    public void setScheduleDetails(List<ScheduleDetail> scheduleDetails) {
        this.scheduleDetails = scheduleDetails;
    }
}
