package com.bickyraj.demo.controller;

import com.bickyraj.demo.application.account.GetAccountUseCase;
import com.bickyraj.demo.application.account.WithdrawAmountUseCase;
import com.bickyraj.demo.dto.AccountDTO;
import com.bickyraj.demo.dto.AccountResponseBody;
import com.bickyraj.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class DemoController {

    private final AccountService accountService;
    private final GetAccountUseCase getAccountUseCase;
    private final WithdrawAmountUseCase withdrawAmountUseCase;

    @GetMapping("")
    public String index() {
        System.out.println("current thread is " + Thread.currentThread());
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> {
                try {
                    callInventoryService();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            System.out.println("hello");
        }
        return ("Hello World how are you!");
    }

    private void callInventoryService() throws InterruptedException {
        Runnable task = () -> System.out.println("what are you doing ");
        task.run();
        Thread.sleep(5000);
    }

    @GetMapping("/create-account")
    public String createAccount() {
        AccountDTO accountDTO = AccountDTO.builder()
                .username("bicky")
                .name("bicky")
                .balance(5000d)
                .address("nepal")
                .build();
        accountService.createAccount(accountDTO);
        return "account created";
    }

    @GetMapping("/withdraw")
    public Boolean withdraw() {
        return withdrawAmountUseCase.execute(WithdrawAmountUseCase.Request.of(
                "bicky", 4500d)).getSuccess();
    }

    @GetMapping("/balance")
    public Double balance() {
        return accountService.getBalance("bicky");
    }

    @GetMapping("/get-account/{username}")
    public ResponseEntity<AccountResponseBody> getAccount(@PathVariable String username) {
        GetAccountUseCase.Response response = getAccountUseCase.execute(GetAccountUseCase.Request.of(username));
        return new ResponseEntity<>(AccountResponseBody.of(response.getName(), response.getBalance()), HttpStatus.OK);
    }
}
