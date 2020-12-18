package com.study.business.financial.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 还款计划明细
 */
public class ScheduleDetail {
    //期次
    private int period;
    // 回款本息（元）
    private BigDecimal rePay;
    // 回款利息（元）
    private BigDecimal reInt;
    // 回款本金（元）
    private BigDecimal rePri;
    // 剩余本金（元）
    private BigDecimal balance;
    // 回款日期
    private Date date;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public BigDecimal getRePay() {
        return rePay;
    }

    public void setRePay(BigDecimal rePay) {
        this.rePay = rePay;
    }

    public BigDecimal getReInt() {
        return reInt;
    }

    public void setReInt(BigDecimal reInt) {
        this.reInt = reInt;
    }

    public BigDecimal getRePri() {
        return rePri;
    }

    public void setRePri(BigDecimal rePri) {
        this.rePri = rePri;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
