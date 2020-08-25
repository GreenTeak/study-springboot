package com.study.business.people.service;

import com.study.business.people.entity.PeUser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PeUserService {
    Page<PeUser> getUserList(int pageNum, int pageSize);

    PeUser findUserById(long id);

    void save(PeUser user);

    void edit(PeUser user);

    void delete(long id);

    /**
     * 构造学生
     * 每天插入一条用户信息，时间从现在起就近的N天
     */
    List<PeUser> buildPeUser(int days);
}
