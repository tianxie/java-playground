package com.github.tianxie.springsecurityrbac.dao;

import com.github.tianxie.springsecurityrbac.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Override
    void delete(User user);

}