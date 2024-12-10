package com.bickyraj.demo.dto.restaurant;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateRestaurantRequestBody {
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "address is required")
    private String address;
    @NotBlank(message = "phone is required")
    private String phone;
    @NotBlank(message = "email is required")
    private String email;
}
