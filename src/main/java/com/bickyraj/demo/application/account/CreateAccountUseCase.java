package com.bickyraj.demo.application.account;

import com.bickyraj.demo.application.UseCase;
import com.bickyraj.demo.entity.Account;
import com.bickyraj.demo.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAccountUseCase extends UseCase<CreateAccountUseCase.Request, CreateAccountUseCase.Response> {

    @AllArgsConstructor(staticName = "of")
    public static class Request {
        private final String name;
        private final String username;
        private final String address;
        private final Double balance;
        private final String email;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class Response {
        private final boolean success;
    }

    private final AccountService accountService;

    @Override
    public Response execute(Request request) {
        Account account = Account.builder()
                .name(request.name)
                .username(request.username)
                .balance(request.balance)
                .address(request.address)
                .phone("987654321")
                .email(request.email)
                .build();
        boolean success = accountService.createAccount(account);
        return new Response(success);
    }
}
