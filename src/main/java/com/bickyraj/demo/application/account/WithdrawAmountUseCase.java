package com.bickyraj.demo.application.account;

import com.bickyraj.demo.application.UseCase;
import com.bickyraj.demo.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WithdrawAmountUseCase extends UseCase<WithdrawAmountUseCase.Request, WithdrawAmountUseCase.Response> {

    @AllArgsConstructor(staticName = "of")
    public static class Request {
        private final String username;
        private final Double amount;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class Response {
        private final Boolean success;
    }

    private final AccountService accountService;

    @Override
    public Response execute(Request request) {
        accountService.withDrawMoney(request.username, request.amount);
        return new Response(true);
    }
}
