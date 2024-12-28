package com.bickyraj.demo.application.account;

import com.bickyraj.demo.application.UseCase;
import com.bickyraj.demo.entity.Account;
import com.bickyraj.demo.service.AccountService;
import com.bickyraj.demo.valueobject.AccountId;
import jakarta.persistence.LockModeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;

@Component
@RequiredArgsConstructor
public class GetAccountUseCase extends UseCase<GetAccountUseCase.Request, GetAccountUseCase.Response> {

    @AllArgsConstructor(staticName = "of")
    public static class Request {
        private final String username;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class Response {
        private final String name;
        private final String email;
        private final Double balance;
    }

    private final AccountService accountService;

    @Override
    public Response execute(Request request) {
        try {
            KeyGenerator key = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        AccountId accountId = AccountId.of(request.username);
        Account accountModel = accountService.getAccountByUsername(request.username, LockModeType.NONE);
        return Response.of(accountModel.getName(), accountModel.getEmail(), accountModel.getBalance());
    }
}
