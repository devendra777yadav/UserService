package com.example.userservicedemo.service;

import com.example.userservicedemo.entity.User;
import com.example.userservicedemo.payload.LoginDto;
import com.example.userservicedemo.payload.UserDto;

import java.util.List;
import java.util.Optional;

/**
 *  Interface of UserService
 */
public interface UserService {
    UserDto registerUser(UserDto userDto);
    boolean loginUser(LoginDto loginDto);
    List<UserDto> getAllUsers();
    UserDto getUserById(long id);
}
