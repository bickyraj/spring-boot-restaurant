package com.bickyraj.demo.application.account;

import com.bickyraj.demo.application.UseCase;
import com.bickyraj.demo.entity.Account;
import com.bickyraj.demo.service.AccountService;
import jakarta.persistence.LockModeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAccountUseCase implements UseCase<GetAccountUseCase.Request, GetAccountUseCase.Response> {

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
        Account accountModel = accountService.getAccountByUsername(request.username, LockModeType.NONE);
        return Response.of(accountModel.getName(), accountModel.getEmail(), accountModel.getBalance());
    }
}
