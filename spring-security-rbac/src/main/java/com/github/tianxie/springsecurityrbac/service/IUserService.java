package com.github.tianxie.springsecurityrbac.service;

import com.github.tianxie.springsecurityrbac.entity.User;
import com.github.tianxie.springsecurityrbac.web.dto.UserDto;

public interface IUserService {

    User registerNewUserAccount(UserDto accountDto);
}
