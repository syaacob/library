package com.saiful.library.controller;

import com.saiful.library.domain.RegisterBorrower;
import com.saiful.library.service.BorrowerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {

    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @PostMapping
    public ResponseEntity<Long> register(@Valid @RequestBody RegisterBorrower request){
        borrowerService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
