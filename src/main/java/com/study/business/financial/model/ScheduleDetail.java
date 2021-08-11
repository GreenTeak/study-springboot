package com.study.business.financial.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 还款计划明细
 */
public class ScheduleDetail {

    // 明细主键 2021-08-12
    private Integer scheduleDetailId;
    // 计划主键（父主键） 2021-08-12
    private Integer scheduleInfoId;
    //期次 2021-08-12
    private int batchNo;
    // 回款本息（元）
    private BigDecimal repayPayAmt;
    // 回款利息（元）
    private BigDecimal repayIntAmt;
    // 回款本金（元）
    private BigDecimal repayPriAmt;
    // 剩余本金（元）
    private BigDecimal surPriAmt;
    // 剩余利息（元）
    private BigDecimal surIntAmt;
    // 回款日期
    private Date repayDate;
    // 创建时间 2021-08-12
    private Date createDate;

    public int getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(int batchNo) {
        this.batchNo = batchNo;
    }

    public BigDecimal getRepayPayAmt() {
        return repayPayAmt;
    }

    public void setRepayPayAmt(BigDecimal repayPayAmt) {
        this.repayPayAmt = repayPayAmt;
    }

    public BigDecimal getRepayIntAmt() {
        return repayIntAmt;
    }

    public void setRepayIntAmt(BigDecimal repayIntAmt) {
        this.repayIntAmt = repayIntAmt;
    }

    public BigDecimal getRepayPriAmt() {
        return repayPriAmt;
    }

    public void setRepayPriAmt(BigDecimal repayPriAmt) {
        this.repayPriAmt = repayPriAmt;
    }

    public BigDecimal getSurPriAmt() {
        return surPriAmt;
    }

    public void setSurPriAmt(BigDecimal surPriAmt) {
        this.surPriAmt = surPriAmt;
    }

    public BigDecimal getSurIntAmt() {
        return surIntAmt;
    }

    public void setSurIntAmt(BigDecimal surIntAmt) {
        this.surIntAmt = surIntAmt;
    }

    public Date getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }

    public Integer getScheduleDetailId() {
        return scheduleDetailId;
    }

    public void setScheduleDetailId(Integer scheduleDetailId) {
        this.scheduleDetailId = scheduleDetailId;
    }

    public Integer getScheduleInfoId() {
        return scheduleInfoId;
    }

    public void setScheduleInfoId(Integer scheduleInfoId) {
        this.scheduleInfoId = scheduleInfoId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
