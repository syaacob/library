package com.saiful.library.converter;

import com.saiful.library.domain.Book;
import com.saiful.library.entity.BookEntity;

public class BookConverter {
    public static Book convert(BookEntity entity) {
        Book book = new Book();
        book.setId(entity.getId());
        book.setTitle(entity.getTitle());
        book.setAuthor(entity.getAuthor());
        book.setIsbn(entity.getIsbn());
        book.setStatus(entity.getBookStatus());
        return book;
    }
}
