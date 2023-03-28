package com.example.userservicedemo.controller;

import com.example.userservicedemo.payload.LoginDto;
import com.example.userservicedemo.payload.UserDto;
import com.example.userservicedemo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
       boolean status = userService.loginUser(loginDto);
       if(status){
           return new ResponseEntity<>("User Signed in Successfully !!", HttpStatus.OK);
       }
        return new ResponseEntity<>("Username or Password is incorrect !! ", HttpStatus.OK);
    }
}
