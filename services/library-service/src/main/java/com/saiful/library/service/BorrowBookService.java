package com.saiful.library.service;

import com.saiful.library.domain.BorrowBookRequest;
import com.saiful.library.domain.ReturnBook;
import com.saiful.library.domain.ReturnBookRequest;
import jakarta.validation.Valid;

public interface BorrowBookService {
    Long borrowBook(@Valid BorrowBookRequest request);

    ReturnBook returnBook(@Valid ReturnBookRequest request);
}
