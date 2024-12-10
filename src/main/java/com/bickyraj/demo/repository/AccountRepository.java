package com.bickyraj.demo.repository;

import com.bickyraj.demo.entity.Account;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findByUsername(String username, LockModeType lockMode);
    Optional<Account> findById(Long id);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    boolean save(Account account);
    Boolean updateBalance(Account account, Double amount);
}
