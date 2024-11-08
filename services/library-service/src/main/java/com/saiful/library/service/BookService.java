package com.saiful.library.service;

import com.saiful.library.domain.RegisterBook;
import jakarta.validation.Valid;

public interface BookService {
    Long register(@Valid RegisterBook request);
}
