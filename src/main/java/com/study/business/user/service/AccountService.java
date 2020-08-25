package com.study.business.user.service;

public interface AccountService {

    /**
     * 转账
     *
     * @param fromUserId 付钱用户id
     * @param toUserId   收钱用户id
     * @param account    交易金额
     */
    void transferAccounts(int fromUserId, int toUserId, float account);
}
