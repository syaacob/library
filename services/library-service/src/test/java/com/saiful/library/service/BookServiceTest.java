package com.saiful.library.service;

import com.saiful.library.domain.RegisterBook;
import com.saiful.library.entity.BookEntity;
import com.saiful.library.exception.BookException;
import com.saiful.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    void testSaveBookShouldSuccess(){
        Mockito.when(bookRepository.findAllByIsbn("123",
                PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id"))))
                .thenReturn(new PageImpl<>(List.of(existingIsbn())));
        bookService = new BookServiceImpl(bookRepository);

        RegisterBook request = new RegisterBook();
        request.setIsbn("123");
        request.setAuthor("author");
        request.setTitle("title");
        bookService.register(request);
    }

    @Test
    void testSaveBookShouldFailedAuthorMissMatchIsbn(){
        Mockito.when(bookRepository.findAllByIsbn("123",
                        PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id"))))
                .thenReturn(new PageImpl<>(List.of(existingIsbn())));
        bookService = new BookServiceImpl(bookRepository);


        RegisterBook request = new RegisterBook();
        request.setIsbn("123");
        request.setAuthor("author-1");
        request.setTitle("title");
        assertThrows(BookException.class, () -> bookService.register(request));
    }

    @Test
    void testSaveBookShouldFailedTitleMissMatchIsbn(){
        Mockito.when(bookRepository.findAllByIsbn("123",
                        PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id"))))
                .thenReturn(new PageImpl<>(List.of(existingIsbn())));
        bookService = new BookServiceImpl(bookRepository);


        RegisterBook request = new RegisterBook();
        request.setIsbn("123");
        request.setAuthor("author");
        request.setTitle("title-1");
        assertThrows(BookException.class, () -> bookService.register(request));
    }

    private BookEntity existingIsbn() {
        BookEntity entity = new BookEntity();
        entity.setIsbn("123");
        entity.setTitle("title");
        entity.setAuthor("author");
        entity.setId(100L);
        return entity;
    }

}