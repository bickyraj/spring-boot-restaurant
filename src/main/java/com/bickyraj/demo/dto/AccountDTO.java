package com.bickyraj.demo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username")
public class AccountDTO {
    private String name;
    private String username;
    private String address;
    private Double balance;
}
