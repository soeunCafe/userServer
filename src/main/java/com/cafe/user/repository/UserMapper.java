package com.cafe.user.repository;

import com.cafe.user.model.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDto> getUser();
    UserDto getUserById(UserDto userDto);
    Integer postUser(UserDto userDto);
    Integer deleteUserById(UserDto userDto);
    Integer updateUserById(UserDto userDto);

    UserDto loginUser(UserDto userDto);

    UserDto getUserByMe(UserDto userDto);
}
