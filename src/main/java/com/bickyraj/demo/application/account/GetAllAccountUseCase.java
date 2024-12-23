package com.bickyraj.demo.application.account;

import com.bickyraj.demo.application.UseCase;
import com.bickyraj.demo.application.EmptyRequest;
import com.bickyraj.demo.entity.Account;
import com.bickyraj.demo.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllAccountUseCase extends UseCase<EmptyRequest, GetAllAccountUseCase.Response> {

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class Response {
        private final List<Account> accounts;
    }

    private final AccountRepository accountRepository;

    @Override
    public Response execute() {
        return Response.of(accountRepository.all());
    }
}
