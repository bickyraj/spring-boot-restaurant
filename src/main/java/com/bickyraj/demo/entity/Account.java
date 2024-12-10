package com.bickyraj.demo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {
    private final Long id;
    private final String username;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Double balance;
}
