package com.bickyraj.demo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Restaurant {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
}
