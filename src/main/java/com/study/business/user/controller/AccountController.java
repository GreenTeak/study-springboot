package com.study.business.user.controller;

import com.study.business.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试事务
 * @date 2019-12-25
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 测试事务
     * @date 2019-12-25
     * @return
     */
    @RequestMapping("/transfer")
    public String transferAccounts() {
        try {
            accountService.transferAccounts(1, 2, 200);
            return "ok";
        } catch (Exception e) {
            return "no e:" + e.getMessage();
        }
    }

}
