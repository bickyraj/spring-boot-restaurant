package com.bickyraj.demo.dto.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "username")
public class CreateAccountRequestBody {
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String address;
    @NotNull
    @Positive
    private Double balance;
    @NotBlank
    private String email;
}
