package com.study.business.user.service.impl;

import com.study.business.user.entity.User;
import com.study.business.user.mapper.UserMapper;
import com.study.business.user.service.UserService;
import com.study.common.constant.BaseConstans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUser() {
        List<User> users = new ArrayList<>();
        try {
            users = userMapper.getUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    //根据id删除用户
    @Override
    public void deleteUser(int id) {
        try {
            userMapper.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //新增用户
    @Override
    public void addUser(User user) {
        try {
            String result = this.checkUser(user, BaseConstans.CREATE);
            if (BaseConstans.SUCCESS.equals(result)) {
                userMapper.addUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改用户
    @Override
    public void updateUser(User user) {
        try {
            String result = this.checkUser(user, BaseConstans.UPDATE);
            if (BaseConstans.SUCCESS.equals(result)) {
                userMapper.updateUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //登陆 2020-01-02
    public Boolean login(User user) {
        Boolean flag = false;
        User u = userMapper.login(user);
        if (u != null) {
            flag = true;
        }
        return flag;
    }

    // 根据用户id查询用户信息
    @Override
    public User getUserById(Long userId) {
        return userMapper.getUserById(userId);
    }


    /**
     * 校验需要入库的用户信息
     *
     * @param user    用户信息
     * @param operate 操作类型：1-保存，2-修改
     * @return
     */
    @Override
    public String checkUser(User user, String operate) {
        String result = BaseConstans.SUCCESS;
        if (BaseConstans.CREATE.equals(operate) || BaseConstans.UPDATE.equals(operate)) {
            if (user.getUserName() == null) {
                result = "fail";
                logger.error("用户名称不能为空");
            }
            if (user.getPassword() == null) {
                result = "fail";
                logger.error("用户密码不能为空");
            }
        }
        return result;
    }


}
