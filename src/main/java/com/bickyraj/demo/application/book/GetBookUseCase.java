package com.bickyraj.demo.application.book;

import com.bickyraj.demo.application.UseCase;
import com.bickyraj.demo.entity.Account;
import com.bickyraj.demo.infrastructure.client.book.BookDto;
import com.bickyraj.demo.infrastructure.service.BookService;
import com.bickyraj.demo.service.AccountService;
import jakarta.persistence.LockModeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetBookUseCase extends UseCase<GetBookUseCase.Request, GetBookUseCase.Response> {

    @AllArgsConstructor(staticName = "of")
    public static class Request {
        private final Long id;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class Response {
        private final BookDto book;
    }

    private final BookService bookService;

    @Override
    public Response execute(Request request) {
        BookDto book = bookService.findById(request.id);
        return Response.of(book);
    }
}
