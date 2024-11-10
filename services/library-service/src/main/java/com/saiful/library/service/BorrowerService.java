package com.saiful.library.service;

import com.saiful.library.domain.Borrower;
import com.saiful.library.domain.RegisterBorrower;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface BorrowerService {
    Long register(@Valid RegisterBorrower request);

    Page<Borrower> listAll(int page, int size);
}
