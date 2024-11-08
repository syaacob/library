package com.saiful.library.controller;

import com.saiful.library.domain.RegisterBook;
import com.saiful.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterBook request){
        bookService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
