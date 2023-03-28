package com.example.userservicedemo.service;

import com.example.userservicedemo.payload.LoginDto;
import com.example.userservicedemo.payload.UserDto;

public interface UserService {
    UserDto registerUser(UserDto userDto);

    boolean loginUser(LoginDto loginDto);
}
