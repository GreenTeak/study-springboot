package com.study.business.people.service.impl;

import com.study.business.people.dao.PeUserRepository;
import com.study.business.people.entity.PeUser;
import com.study.business.people.service.PeUserService;
import com.study.common.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PeUserServiceImpl implements PeUserService {

    @Autowired
    private PeUserRepository userRepository;

    @Override
    public Page<PeUser> getUserList(int pageNum, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        Page<PeUser> users = userRepository.findAll(pageable);

        return users;
    }

    @Override
    public PeUser findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(PeUser user) {
        userRepository.save(user);
    }

    @Override
    public void edit(PeUser user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    /**
     * 构造学生
     * 每天插入一条用户信息，时间从现在起就近的N天
     */
    @Override
    public List<PeUser> buildPeUser(int days) {
        List<PeUser> peUsers = new ArrayList<>();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < days; i++) {
            calendar.add(Calendar.DATE, -i);
            Date createDate = calendar.getTime();
            String date = format.format(createDate);
            PeUser peUser = new PeUser();
            String name = RandomUtil.getChineseName();
            String email = RandomUtil.getEmail(0, i + 1);
            String stuSex = RandomUtil.getSex();
            peUser.setUserName(name);
            peUser.setEmail(email);
            peUser.setPassWord("123456");// 默认密码
            peUser.setStuSex(stuSex);
            peUser.setCreateDate(createDate);
            peUser.setRegTime(date);
            peUser.setNickName(name);
            peUsers.add(peUser);

        }

        userRepository.saveAll(peUsers);
        return peUsers;
    }
}
