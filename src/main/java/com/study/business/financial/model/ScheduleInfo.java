package com.study.business.financial.model;

import java.math.BigDecimal;

public class ScheduleInfo {

    // 本金总额
    private BigDecimal total;
    // 年利率
    private BigDecimal yearRate;
    // 总期数
    private int sumTerm;
    // 起息日
    private String startDate;

    // 还款计划方式
    private String type;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getYearRate() {
        return yearRate;
    }

    public void setYearRate(BigDecimal yearRate) {
        this.yearRate = yearRate;
    }

    public int getSumTerm() {
        return sumTerm;
    }

    public void setSumTerm(int sumTerm) {
        this.sumTerm = sumTerm;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ScheduleInfo{" +
                "total=" + total +
                ", yearRate=" + yearRate +
                ", sumTerm=" + sumTerm +
                ", startDate='" + startDate + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
