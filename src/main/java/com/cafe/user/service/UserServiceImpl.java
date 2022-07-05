package com.cafe.user.service;

import com.cafe.user.model.UserDto;
import com.cafe.user.repository.UserMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserDto> findUser() {return userMapper.getUser();}
    @Override
    public UserDto findUserById(UserDto userDto) {return userMapper.getUserById(userDto);}
    @Override
    public Integer insertUser(UserDto userDto){return userMapper.postUser(userDto);}
    @Override
    public Integer deleteUserById(UserDto userDto) {return userMapper.deleteUserById(userDto);}
    @Override
    public Integer updateUserById(UserDto userDto){return userMapper.updateUserById(userDto);}

    @Override
    public UserDto loginUser(UserDto userDto) { return userMapper.loginUser(userDto); };

    @Override
    public UserDto getUserByMe(UserDto userDto) {return userMapper.getUserByMe(userDto);};


}
