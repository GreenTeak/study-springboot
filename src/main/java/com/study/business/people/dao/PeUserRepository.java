package com.study.business.people.dao;

import com.study.business.people.entity.PeUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeUserRepository extends JpaRepository<PeUser, Long> {

    PeUser findById(long id);
    void deleteById(long id);
}
