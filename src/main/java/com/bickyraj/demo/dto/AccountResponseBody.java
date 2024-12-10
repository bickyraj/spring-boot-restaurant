package com.bickyraj.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class AccountResponseBody {
    String name;
    Double balance;
}
