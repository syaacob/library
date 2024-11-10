package com.saiful.library.service;


import com.saiful.library.domain.RegisterBook;
import com.saiful.library.exception.BookException;
import com.saiful.library.repository.BookRepository;
import jakarta.validation.Valid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("classpath:book.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookServiceIntegrationTesting {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testSaveNewBookShouldSuccess() {
        @Valid RegisterBook request = new RegisterBook();
        request.setTitle("Dear World");
        request.setIsbn("9781471169557");
        request.setAuthor("Bana Alabed");
        Long bookId = bookService.register(request);
    }

    @Test
    void testSaveNewBookWithSameTitleShouldSuccess() {
        RegisterBook request = new RegisterBook();
        request.setTitle("Dear World");
        request.setIsbn("9781471169557");
        request.setAuthor("Bana Alabed");

        RegisterBook request1 = new RegisterBook();
        request1.setTitle("dear world");
        request1.setIsbn("9781471169557");
        request1.setAuthor("Bana Alabed");

        Long bookId = bookService.register(request);
        Long bookId1 = bookService.register(request1);
    }

    @Test
    void testSaveNewBookWithSameIsbnShouldFailed() {
        RegisterBook request = new RegisterBook();
        request.setTitle("Dear World");
        request.setIsbn("9781471169557");
        request.setAuthor("Bana Alabed");

        RegisterBook request1 = new RegisterBook();
        request1.setTitle("dear world hard cover");
        request1.setIsbn("9781471169557");
        request1.setAuthor("Bana Alabed");

        bookService.register(request);
        assertThrows(BookException.class, () -> bookService.register(request1));
    }
}
