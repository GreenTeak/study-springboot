package com.study.business.user.service.impl;

import com.study.business.user.entity.Account;
import com.study.business.user.mapper.AccountMapper;
import com.study.business.user.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    /**
     * 转账
     *
     * @param fromUserId 付钱用户id
     * @param toUserId   收钱用户id
     * @param account    交易金额
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transferAccounts(int fromUserId, int toUserId, float account) {
        Account fromAcct = accountMapper.getAccount(fromUserId);
        fromAcct.setBalance(fromAcct.getBalance() - account);
        accountMapper.updateBalance(fromAcct);

        Account toAccount = accountMapper.getAccount(toUserId);
        toAccount.setBalance(toAccount.getBalance() + account);

        //假设转账的时候假如出现异常，业务类或业务方法中没有使用@Transactional控制事务，则会出现钱转出了，收钱人没有收到的情况
//        int zero = 1/0;   //打开这个异常

        accountMapper.updateBalance(toAccount);
    }
}
