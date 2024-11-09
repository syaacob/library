package com.saiful.library.domain;

import com.saiful.library.entity.BookStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private BookStatus status;
}
