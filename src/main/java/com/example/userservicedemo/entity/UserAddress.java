package com.example.userservicedemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userAddress")

public class UserAddress {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long addressId;

   @Column(name="address")
    private String address;

//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name="userId",referencedColumnName = "id")
//    private User user;

}
