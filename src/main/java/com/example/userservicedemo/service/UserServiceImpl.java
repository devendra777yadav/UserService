package com.example.userservicedemo.service;

import com.example.userservicedemo.entity.User;
import com.example.userservicedemo.entity.UserAddress;
import com.example.userservicedemo.exception.UserExistException;
import com.example.userservicedemo.exception.UserNotExistException;
import com.example.userservicedemo.payload.LoginDto;
import com.example.userservicedemo.payload.UserDto;
import com.example.userservicedemo.repository.UserAddressRepository;
import com.example.userservicedemo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  UserServiceImpl class which implements UserService
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private ObjectMapper mapper;


    /**
     * @param userDto
     * @return userdto {@link UserDto}
     */
    @Override
    public UserDto registerUser(UserDto userDto) {
        Optional<User> existingUser = userRepository.findByusername(userDto.getUsername());
        if(existingUser.isPresent()){
            log.info("User Already Registered");
            throw new UserExistException("User already exists");
        }
        User newUser=mapper.convertValue(userDto, User.class); // x

        List<UserAddress> userAddressList = userDto.getUserAddressList();
        newUser.setUserAddressList(userAddressList);
        userRepository.save(newUser);
        userAddressRepository.saveAll(userAddressList);
        UserDto newAddedUser = mapper.convertValue(newUser,UserDto.class);
        log.info("User Successfully registered");
        return newAddedUser;
    }

    /**
     * @param loginDto
     * @return boolean return true if user found is userRepository and false if not
     */
    @Override
    public boolean loginUser(LoginDto loginDto) {
        Optional<User> existingUser = userRepository.findByusername(loginDto.getUsername());
        if(existingUser.isPresent() && existingUser.get().getPassword().equals(loginDto.getPassword())){
            log.info("User with Username" + loginDto.getUsername() + "logged successfully!");
            return true;
        }
        return false;
    }

    /**
     * @return List of all user with their UserDto{@link List<UserDto>}
     */
    @Override
    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        log.info("All the users are accessible");
        return users.stream().map(user->mapper.convertValue(user,UserDto.class
        )).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotExistException("User with id " + id +" does not exist"));
        return mapper.convertValue(user, UserDto.class);
    }
}
