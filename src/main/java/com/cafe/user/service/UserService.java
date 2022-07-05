package com.cafe.user.service;

import com.cafe.user.model.UserDto;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {
    List<UserDto> findUser();
    UserDto findUserById(UserDto userDto);
    Integer insertUser(UserDto userDto);
    Integer deleteUserById(UserDto userDto);
    Integer updateUserById(UserDto userDto);

    UserDto loginUser(UserDto userDto);

    UserDto getUserByMe(UserDto userDto);
}
