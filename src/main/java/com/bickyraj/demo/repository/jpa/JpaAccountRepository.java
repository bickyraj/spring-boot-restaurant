package com.bickyraj.demo.repository.jpa;

import com.bickyraj.demo.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaAccountRepository extends JpaRepository<AccountModel, Long> {
    @Query(
            value = "SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END " +
                    "FROM accounts a WHERE a.email = :email OR a.username = :username",
            nativeQuery = true

    )
    boolean existsByEmail(String email, String username);
}
