package com.saiful.library.controller;

import com.saiful.library.domain.Book;
import com.saiful.library.domain.RegisterBook;
import com.saiful.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @Operation(
            description = "register new book API",
            summary = "api for register new book"

    )
    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterBook request){
        bookService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            description = "search book API",
            summary = "api for search book"

    )
    @GetMapping
    public ResponseEntity<Page<Book>> searchBook(@RequestParam(required = false, defaultValue = "30") Integer size,
                                                 @RequestParam(required = false, defaultValue = "0") Integer page) {

        return ResponseEntity.ok(bookService.searchBook(size, page));

    }
}
