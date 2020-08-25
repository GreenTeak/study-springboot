package com.study.business.user.mapper;

import com.study.business.user.entity.Account;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface AccountMapper {

    // 获取账户
    Account getAccount(int fromUserId);

    // 扣钱
    void updateBalance(Account account);
}
