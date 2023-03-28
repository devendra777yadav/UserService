package com.example.userservicedemo.payload;
import com.example.userservicedemo.entity.UserAddress;
import lombok.Data;

import java.util.List;

/**
 *  Class for UserDto
 */
@Data
public class UserDto {
    private int id;

    private String name;

    private String username;

    private String email;

    private String password;

    private List<UserAddress> userAddressList;
}
