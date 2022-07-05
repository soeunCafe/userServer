package com.cafe.user.controller;

import com.cafe.user.aspect.TokenRequired;
import com.cafe.user.config.SecurityService;
import com.cafe.user.model.LoginDto;
import com.cafe.user.model.UserDto;
import com.cafe.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    SecurityService securityService;

    @GetMapping("/")
    public List<UserDto> getUser() {
        return userService.findUser();
    }
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable String id) {
        UserDto userDto = new UserDto();
        userDto.setId(Integer.valueOf(id));
        return userService.findUserById(userDto);
    }
//    @PostMapping("/login")
//    public Integer loginUser(@RequestBody UserDto userDto) {
//        return userService.insertUser(userDto);
//    }
    @DeleteMapping("/{id}")
    public Integer deleteUserById(@PathVariable String id){
        UserDto userDto = new UserDto();
        userDto.setId(Integer.valueOf(id));
        return userService.deleteUserById(userDto);
    }
    @PutMapping("/{id}")
    public Integer updateUserById(@RequestBody UserDto userDto, @PathVariable String id) {
        userDto.setId(Integer.valueOf(id));
        return userService.updateUserById(userDto);
    }
    @GetMapping("/me")
    // @TokenRequired
    public UserDto getUserByMe() {

        UserDto userDto = new UserDto();
        userDto.setId(Integer.valueOf(securityService.getIdAtToken()));

        return userService.findUserById(userDto);
    }

    @GetMapping("/token")
    @TokenRequired
    public String getToken(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String tokenBearer = request.getHeader("Authorization");
        String subject = securityService.getSubject(tokenBearer);
        return subject;
    }


    @PostMapping("/login")
    @Operation(description = "로그인")
    public LoginDto loginUser(@RequestBody UserDto userDto) {
        UserDto user =  userService.loginUser(userDto);
        String token = securityService.createToken(user);

        LoginDto loginDto = new LoginDto();
        loginDto.setToken(token);
        loginDto.setName(user.getName());
        loginDto.setImg(user.getImg());

        return loginDto;
    }


}
