package com.saiful.library.service;

import com.saiful.library.domain.BorrowBookRequest;
import com.saiful.library.entity.BookEntity;
import com.saiful.library.entity.BookStatus;
import com.saiful.library.entity.BorrowerEntity;
import com.saiful.library.exception.BookBorrowerException;
import com.saiful.library.repository.BookBorrowerRepository;
import com.saiful.library.repository.BookRepository;
import com.saiful.library.repository.BorrowerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BorrowBookServiceTest {

    private BorrowBookService borrowBookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowerRepository borrowerRepository;

    @Mock
    private BookBorrowerRepository bookBorrowerRepository;

    @Test
    void testBorrowBookShouldSuccess() {

        Mockito.when(bookRepository.findById(100L))
                .thenReturn(Optional.of(getAvailableBook()));

        Mockito.when(borrowerRepository.findById(1L))
                .thenReturn(Optional.of(getBorrower()));

        borrowBookService = new BorrowBookServiceImpl(bookRepository, borrowerRepository, bookBorrowerRepository);

        BorrowBookRequest request = new BorrowBookRequest();
        request.setBorrowerId(1L);
        request.setBookId(100L);
        borrowBookService.borrowBook(request);
    }

    @Test
    void testBorrowBookShouldFailedBookNotAvailable() {

        Mockito.when(bookRepository.findById(110L))
                .thenReturn(Optional.of(getUnAvailableBook()));

        Mockito.when(borrowerRepository.findById(1L))
                .thenReturn(Optional.of(getBorrower()));

        borrowBookService = new BorrowBookServiceImpl(bookRepository, borrowerRepository, bookBorrowerRepository);

        BorrowBookRequest request = new BorrowBookRequest();
        request.setBorrowerId(1L);
        request.setBookId(110L);
        assertThrows(BookBorrowerException.class,() -> borrowBookService.borrowBook(request));
    }

    @Test
    void testBorrowBookShouldFailedBookNotExist() {

        Mockito.when(bookRepository.findById(120L))
                .thenReturn(Optional.empty());

        Mockito.when(borrowerRepository.findById(1L))
                .thenReturn(Optional.of(getBorrower()));

        borrowBookService = new BorrowBookServiceImpl(bookRepository, borrowerRepository, bookBorrowerRepository);

        BorrowBookRequest request = new BorrowBookRequest();
        request.setBorrowerId(1L);
        request.setBookId(120L);
        assertThrows(BookBorrowerException.class,() -> borrowBookService.borrowBook(request));
    }

    @Test
    void testBorrowBookShouldFailedBorrowerNotExist() {

        Mockito.when(borrowerRepository.findById(2L))
                .thenReturn(Optional.empty());

        borrowBookService = new BorrowBookServiceImpl(bookRepository, borrowerRepository, bookBorrowerRepository);

        BorrowBookRequest request = new BorrowBookRequest();
        request.setBorrowerId(2L);
        request.setBookId(120L);
        assertThrows(BookBorrowerException.class,() -> borrowBookService.borrowBook(request));
    }

    private BorrowerEntity getBorrower() {
        BorrowerEntity borrowerEntity = new BorrowerEntity();
        borrowerEntity.setId(1L);
        borrowerEntity.setName("dexter morgan");
        borrowerEntity.setEmail("dexter@killer.com");
        return borrowerEntity;
    }

    private BookEntity getAvailableBook(){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(100L);
        bookEntity.setBookStatus(BookStatus.AVAILABLE);
        bookEntity.setIsbn("9780891419198");
        bookEntity.setTitle("with the old breed");
        bookEntity.setAuthor("e.b sledge");
        return bookEntity;
    }

    private BookEntity getUnAvailableBook(){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(110L);
        bookEntity.setBookStatus(BookStatus.BORROWED);
        bookEntity.setIsbn("9780891419198");
        bookEntity.setTitle("with the old breed");
        bookEntity.setAuthor("e.b sledge");
        return bookEntity;
    }
}