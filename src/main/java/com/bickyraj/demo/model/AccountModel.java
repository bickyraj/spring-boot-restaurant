package com.bickyraj.demo.model;

import com.bickyraj.demo.entity.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;


@Data
@Entity
@Table(name = "accounts")
public class AccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double balance;

    public Account toEntity() {
        return Account.builder()
                .username(this.username)
                .id(this.id)
                .email(this.email)
                .phone(this.phone)
                .address(this.address)
                .balance(this.balance)
                .build();
    }

    public static AccountModel fromEntity(Account account) {
        AccountModel model = new AccountModel();
        model.setId(account.getId());
        model.setUsername(account.getUsername());
        model.setName(account.getName());
        model.setEmail(account.getEmail());
        model.setPhone(account.getPhone());
        model.setAddress(account.getAddress());
        model.setBalance(account.getBalance());
        return model;
    }
}
