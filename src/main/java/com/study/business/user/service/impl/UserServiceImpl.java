package com.study.business.user.service.impl;

import com.study.business.user.entity.User;
import com.study.business.user.mapper.UserMapper;
import com.study.business.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
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
            userMapper.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改用户
    @Override
    public void updateUser(User user) {
        try {
            userMapper.updateUser(user);
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
}
