package com.study.business.user.entity;

import org.springframework.stereotype.Component;

/**
 * 账户模型
 */
@Component
public class Account {
    // 编号
    private Long id;
    // 用户id
    private Long userId;
    // 用户名
    private String userName;
    // 余额
    private float balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
