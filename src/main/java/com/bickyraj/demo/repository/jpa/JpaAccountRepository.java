package com.bickyraj.demo.repository.jpa;

import com.bickyraj.demo.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<AccountModel, Long> {
}
