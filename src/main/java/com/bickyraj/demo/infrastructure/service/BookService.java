package com.bickyraj.demo.infrastructure.service;

import com.bickyraj.demo.infrastructure.client.book.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class BookService {

    private final WebClient webClient;

    public BookDto findById(Long id) {
        return webClient.get()
                .uri("http://localhost:3032/api/book/" + id)
                .retrieve()
                .bodyToMono(BookDto.class)
                .block();
    }
}
