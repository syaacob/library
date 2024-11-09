package com.saiful.library.controller;

import com.saiful.library.domain.BorrowBookRequest;
import com.saiful.library.domain.ReturnBook;
import com.saiful.library.domain.ReturnBookRequest;
import com.saiful.library.service.BorrowBookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookBorrowers")
public class BookBorrowerController {

    private final BorrowBookService borrowBookService;

    public BookBorrowerController(BorrowBookService borrowBookService) {
        this.borrowBookService = borrowBookService;
    }

    @PostMapping
    public ResponseEntity<Void> borrowBook(@Valid @RequestBody BorrowBookRequest request){
        borrowBookService.borrowBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(path = "/returns")
    public ResponseEntity<ReturnBook> returnBook(@Valid @RequestBody ReturnBookRequest request) {
        return ResponseEntity.ok(borrowBookService.returnBook(request));
    }
    
}
