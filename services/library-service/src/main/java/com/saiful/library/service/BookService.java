package com.saiful.library.service;

import com.saiful.library.domain.Book;
import com.saiful.library.domain.RegisterBook;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface BookService {
    Long register(@Valid RegisterBook request);

    Page<Book> searchBook(String key, String searchBy, Integer size, Integer page);
}
