package com.saiful.library.service;

import com.saiful.library.domain.BorrowBookRequest;
import jakarta.validation.Valid;

public interface BorrowBookService {
    Long borrowBook(@Valid BorrowBookRequest request);
}
