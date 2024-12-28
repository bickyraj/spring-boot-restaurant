package com.bickyraj.demo.controller;

import com.bickyraj.demo.application.account.CreateAccountUseCase;
import com.bickyraj.demo.application.account.GetAccountUseCase;
import com.bickyraj.demo.application.account.GetAllAccountUseCase;
import com.bickyraj.demo.application.account.WithdrawAmountUseCase;
import com.bickyraj.demo.application.book.GetBookUseCase;
import com.bickyraj.demo.dto.account.AccountResponseBody;
import com.bickyraj.demo.dto.account.CreateAccountRequestBody;
import com.bickyraj.demo.infrastructure.client.book.BookDto;
import com.bickyraj.demo.service.AccountService;
import com.bickyraj.demo.util.CipherUtil;
import io.micrometer.observation.annotation.Observed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api/account/")
@Slf4j
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final AccountService accountService;
    private final GetAccountUseCase getAccountUseCase;
    private final WithdrawAmountUseCase withdrawAmountUseCase;
    private final CreateAccountUseCase createAccountUseCase;
    private final GetAllAccountUseCase getAllAccountUseCase;
    private final GetBookUseCase getBookUseCase;

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

    @PostMapping("/create-account")
    public String createAccount(@Valid @RequestBody CreateAccountRequestBody requestBody) {
        CreateAccountUseCase.Response response = createAccountUseCase.execute(
                CreateAccountUseCase.Request.of(
                        requestBody.getName(),
                        requestBody.getUsername(),
                        requestBody.getAddress(),
                        requestBody.getBalance(),
                        requestBody.getEmail()
                )
        );
        if (response.isSuccess()) {
            return "account created";
        }
        return "error creating account";
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

    @Observed(name = "api.account_all.response.time.seconds")
    @GetMapping("/all")
    public ResponseEntity<List<AccountResponseBody>> getAllAccounts() {
        GetAllAccountUseCase.Response response = getAllAccountUseCase.execute();
        return new ResponseEntity<>(response.getAccounts()
                .stream()
                .map(a -> AccountResponseBody.of(a.getName(), a.getBalance())).toList(), HttpStatus.OK);
    }

    @GetMapping("/get-book/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        GetBookUseCase.Response response = getBookUseCase.execute(GetBookUseCase.Request.of(id));
        return new ResponseEntity<>(response.getBook(), HttpStatus.OK);
    }

    @GetMapping("/generate-secret")
    public SecretKey generateSecret() throws NoSuchAlgorithmException {
        return CipherUtil.generateSecretKey();
    }
}
