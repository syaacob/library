package com.saiful.library.service;

import com.saiful.library.domain.BorrowBookRequest;
import com.saiful.library.domain.ReturnBook;
import com.saiful.library.domain.ReturnBookRequest;
import com.saiful.library.entity.BookBorrowerEntity;
import com.saiful.library.entity.BookStatus;
import com.saiful.library.exception.BookBorrowerException;
import com.saiful.library.repository.BookBorrowerRepository;
import com.saiful.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql("classpath:borrowBook.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BorrowBookServiceIntgTest {
    @Autowired
    private BorrowBookService borrowBookService;

    @Autowired
    private BookBorrowerRepository bookBorrowerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testBorrowBookShouldSuccess() {
        BorrowBookRequest request = new BorrowBookRequest();
        request.setBookId(1000L);
        request.setBorrowerId(10L);
        Long results = borrowBookService.borrowBook(request);
        assertNotEquals(0, results);
        List<BookBorrowerEntity> list = bookBorrowerRepository.findAll();
        assertThat(list)
                .extracting("book.id").contains(1000L);
    }

    @Test
    void testBorrowBookShouldFailedBookNotFound() {
        BorrowBookRequest request = new BorrowBookRequest();
        request.setBookId(1900L);
        request.setBorrowerId(10L);
        assertThrows(BookBorrowerException.class,() -> borrowBookService.borrowBook(request));
    }


    @Test
    void testBorrowBookShouldFailedBookNotAvailable() {
        BorrowBookRequest request = new BorrowBookRequest();
        request.setBookId(1001L);
        request.setBorrowerId(10L);
        assertThrows(BookBorrowerException.class,() -> borrowBookService.borrowBook(request));
    }

    @Test
    void test2BorrowRequestWithSameBook() throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable request1 = () -> {
            BorrowBookRequest request = new BorrowBookRequest();
            request.setBookId(1000L);
            request.setBorrowerId(10L);
            borrowBookService.borrowBook(request);
        };

        Runnable request2 = () -> {
            BorrowBookRequest request = new BorrowBookRequest();
            request.setBookId(1000L);
            request.setBorrowerId(20L);
            assertThrows(ObjectOptimisticLockingFailureException.class, () ->
                    borrowBookService.borrowBook(request));
        };

        executorService.submit(request1);
        executorService.submit(request2);
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        List<BookBorrowerEntity> results = bookBorrowerRepository.findAll();
        assertThat(results)
                .extracting("book.id").contains(1000L); // only 1 book should be borrowed.
    }

    @Test
    void testReturnBookShouldSuccess(){
        ReturnBookRequest returnBookRequest = new ReturnBookRequest();
        returnBookRequest.setBookId(1002L);

        ReturnBook returnBook = borrowBookService.returnBook(returnBookRequest);
        assertEquals(returnBook.getBorrowId(), 3000L);
        assertNotNull(returnBook.getReturnDate());
        assertNotNull(returnBook.getDuration());

        BookStatus bookStatus = bookRepository.findById(returnBookRequest.getBookId())
                .get().getBookStatus();
        assertEquals(BookStatus.AVAILABLE, bookStatus);

    }

}
