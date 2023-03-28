package com.example.userservicedemo.payload;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
public class UserDto {
    private int id;

    private String name;

    private String username;

    private String email;

    private String password;
}
