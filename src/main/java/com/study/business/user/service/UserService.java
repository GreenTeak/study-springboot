package com.study.business.user.service;

import com.study.business.user.entity.User;

import java.util.List;

public interface UserService {
    //显示所有用户
    List<User> getUser();

    //根据id删除用户
    void deleteUser(int id);

    //新增用户
    void addUser(User user);

    //修改用户
    void updateUser(User user);

    //登陆 2020-01-02
    Boolean login(User user);

    // 根据用户id查询用户信息
    User getUserById(Long userId);

    // 校验需要入库的用户信息
    String checkUser(User user,String operate);
}
