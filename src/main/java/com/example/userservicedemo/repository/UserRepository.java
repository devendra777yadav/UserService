package com.example.userservicedemo.repository;

import com.example.userservicedemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
