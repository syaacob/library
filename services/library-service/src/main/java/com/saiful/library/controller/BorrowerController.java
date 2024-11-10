package com.saiful.library.controller;

import com.saiful.library.domain.Borrower;
import com.saiful.library.domain.RegisterBorrower;
import com.saiful.library.service.BorrowerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {

    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @Operation(
            description = "register new borrower API",
            summary = "api for register new borrower"

    )
    @PostMapping
    public ResponseEntity<Long> register(@Valid @RequestBody RegisterBorrower request){
        borrowerService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<Borrower>> listAll(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                  @RequestParam(name = "size", required = false, defaultValue = "30") Integer size) {

        return ResponseEntity.ok(borrowerService.listAll(page, size));
    }
}
