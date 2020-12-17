package com.study.business.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.study.business.user.entity.User;

import java.util.List;

//@Mapper
public interface UserMapper {

    //获取用户名单
    List<User> getUser() throws Exception;

    //根据id删除用户
    void deleteUser(int id) throws Exception;

    //新增用户
    void addUser(User user) throws Exception;

    //修改用户
    void updateUser(User user) throws Exception;

    //登陆 2020-01-02
    User login(User user);

    //根据用户id查询用户信息
    User getUserById(Long userId);
}
