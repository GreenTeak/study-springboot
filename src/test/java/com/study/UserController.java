package com.study;

import com.study.business.user.entity.User;
import com.study.business.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 测试类
 *
 * @author zhangpba
 * @date 2021-12-01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    //显示用户列表
    @Test
    public void listUser() {
        List<User> list = userService.getUser();
        for (User u : list) {
            logger.info(u.toString());
        }
    }
}
