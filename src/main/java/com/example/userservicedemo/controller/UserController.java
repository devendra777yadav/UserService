package com.example.userservicedemo.controller;
import com.example.userservicedemo.exception.ErrorResponse;
import com.example.userservicedemo.exception.UserExistException;
import com.example.userservicedemo.exception.UserNotExistException;
import com.example.userservicedemo.payload.LoginDto;
import com.example.userservicedemo.payload.UserDto;
import com.example.userservicedemo.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  Public class of user controller
 */
@RestController
@Log4j2
@RequestMapping("user-service/users")
public class UserController {

    @ExceptionHandler(value = UserNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotExistException(UserNotExistException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = UserExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserExistException(UserExistException ex)
    {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @Autowired
    private UserService userService;

    /**
     * controller for register new user
     * @param {@link userDto}
     * @return ResponseEntity which includes userDto of new user and http status
     */
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        log.info("Register User controller executing");
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
    }

    /**
     * Login controller
     * @param {@link loginDto}
     * @return ResponseEntity which includes authentication status and http status {@link ResponseEntity<String>}
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
        log.info("Login the user");
        boolean status = userService.loginUser(loginDto);
        if(status){
            return new ResponseEntity<>("User Signed in Successfully !!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Username or Password is incorrect !! ", HttpStatus.OK);
    }

    /**
     * @return List of all users {@link List<UserDto>}
     */
    @GetMapping
    public List<UserDto> getAllUsers(){
        log.info("Getting all the user");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }
}
