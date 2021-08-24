package com.github.tianxie.springsecurityrbac.dao;

import com.github.tianxie.springsecurityrbac.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}