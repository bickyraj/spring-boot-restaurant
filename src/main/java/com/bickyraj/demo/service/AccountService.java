package com.bickyraj.demo.service;

import com.bickyraj.demo.entity.Account;
import com.bickyraj.demo.exception.EmailOrUsernameAlreadyExistException;
import com.bickyraj.demo.exception.InsufficientBalanceException;
import com.bickyraj.demo.exception.NotFoundException;
import com.bickyraj.demo.repository.AccountRepository;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public boolean createAccount(Account account) {
        if (accountRepository.existsByEmailAndUsername(account.getEmail(), account.getUsername())) {
            throw new EmailOrUsernameAlreadyExistException();
        }
        accountRepository.save(account);
        return true;
    }

    public Account getAccountByUsername(String username, LockModeType lockMode) throws NotFoundException {
        return accountRepository.findByUsername(username, lockMode).orElseThrow(() -> new NotFoundException("Account not found"));
    }

    @Transactional
    public void withDrawMoney(String username, Double amount) throws InsufficientBalanceException {
        Account account = getAccountByUsername(username, LockModeType.PESSIMISTIC_WRITE);
        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        accountRepository.updateBalance(account, amount);
        System.out.println(account);
    }

    public Double getBalance(String username) {
        Account account = getAccountByUsername(username, LockModeType.NONE);
        return account.getBalance();
    }
}
