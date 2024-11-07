package com.saiful.library.service;

import com.saiful.library.domain.RegisterBorrower;
import jakarta.validation.Valid;

public interface BorrowerService {
    Long register(@Valid RegisterBorrower request);
}
