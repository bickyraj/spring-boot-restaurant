package com.bickyraj.demo.service;

import com.bickyraj.demo.entity.Account;
import com.bickyraj.demo.exception.NotFoundException;
import com.bickyraj.demo.repository.AccountRepository;
import jakarta.persistence.LockModeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.when;

public class AccountModelServiceTests {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAccountNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            when(accountRepository.findByUsername("bicky", LockModeType.NONE))
                    .thenThrow(NotFoundException.class);
            accountService.getAccountByUsername("bicky", LockModeType.NONE);
        });
    }

    @Test
    public void testFindAccountByUsername() {
        when(accountRepository.findByUsername("john", LockModeType.NONE))
                .thenReturn(Optional.ofNullable(Account.builder().username("john").name("john").id(123123L).build()));
        Account account = accountService.getAccountByUsername("john", LockModeType.NONE);
        Assertions.assertEquals("john", account.getUsername());
    }
}
