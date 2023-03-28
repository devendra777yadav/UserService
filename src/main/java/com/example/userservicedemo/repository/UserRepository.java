package com.example.userservicedemo.repository;

import com.example.userservicedemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByusername(String userName);
}
