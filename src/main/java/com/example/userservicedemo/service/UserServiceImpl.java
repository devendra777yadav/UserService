package com.example.userservicedemo.service;

import com.example.userservicedemo.entity.User;
import com.example.userservicedemo.exception.UserExistException;
import com.example.userservicedemo.payload.LoginDto;
import com.example.userservicedemo.payload.UserDto;
import com.example.userservicedemo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    private ObjectMapper mapper;


    @Override
    public UserDto registerUser(UserDto userDto) {
        List<User> users = userRepository.findAll();
        User newUser=mapper.convertValue(userDto, User.class);
        for (User u : users) {
            if (u.getUsername().equals(newUser.getUsername()) || u.getEmail().equals(newUser.getEmail()) ) {
                System.out.println("User Already exists!");
                throw new UserExistException("User already exists", "USER_ALREADY_EXISTS");
            }
        }
        userRepository.save(newUser);
        UserDto newAddedUser = mapper.convertValue(newUser,UserDto.class);
        return newAddedUser;
    }

    @Override
    public boolean loginUser(LoginDto loginDto) {
        List<User> users = userRepository.findAll();
        for (User other : users) {
            if (other.getUsername().equals(loginDto.getUsername()) && other.getPassword().equals(loginDto.getPassword()) ) {
               return true;
            }
        }
        return false;
    }
}
