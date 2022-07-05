package com.cafe.user.model;

import lombok.Data;

@Data
public class LoginDto {
    private String token;
    private String name;
    private String img;
}
