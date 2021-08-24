package com.github.tianxie.springsecurityrbac.dao;

import com.github.tianxie.springsecurityrbac.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

}