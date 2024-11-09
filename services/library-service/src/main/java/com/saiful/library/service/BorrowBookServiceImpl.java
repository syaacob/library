package com.saiful.library.service;

import com.saiful.library.domain.BorrowBookRequest;
import com.saiful.library.domain.ReturnBook;
import com.saiful.library.domain.ReturnBookRequest;
import com.saiful.library.entity.BookBorrowerEntity;
import com.saiful.library.entity.BookEntity;
import com.saiful.library.entity.BookStatus;
import com.saiful.library.entity.BorrowerEntity;
import com.saiful.library.exception.BookBorrowerException;
import com.saiful.library.repository.BookBorrowerRepository;
import com.saiful.library.repository.BookRepository;
import com.saiful.library.repository.BorrowerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class BorrowBookServiceImpl implements BorrowBookService {
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;
    private final BookBorrowerRepository bookBorrowerRepository;

    private static final long BORROW_DURATION = 7; //maximum days to borrow

    public BorrowBookServiceImpl(BookRepository bookRepository, BorrowerRepository borrowerRepository,
                                 BookBorrowerRepository bookBorrowerRepository) {
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
        this.bookBorrowerRepository = bookBorrowerRepository;
    }

    @Transactional
    @Override
    public Long borrowBook(BorrowBookRequest request) {
        log.info("processing {} borrowing {}", request.getBorrowerId(), request.getBookId());

        BorrowerEntity borrowerEntity = borrowerRepository.findById(request.getBorrowerId())
                .orElseThrow(() ->new BookBorrowerException("borrower not exist"));

        BookEntity bookEntity = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new BookBorrowerException("book not exist"));

        if(!BookStatus.AVAILABLE.equals(bookEntity.getBookStatus())){
            throw new BookBorrowerException("book not available");
        }
        log.info("version {}", bookEntity.getVersion());
        bookEntity.setBookStatus(BookStatus.BORROWED);

        BookBorrowerEntity entity = new BookBorrowerEntity();
        entity.setBook(bookEntity);
        entity.setBorrower(borrowerEntity);
        entity.setBorrowDate(LocalDateTime.now());
        entity.setExpectedReturnDate(LocalDateTime.now().plusDays(BORROW_DURATION));

        bookRepository.save(bookEntity);
        bookBorrowerRepository.save(entity);


        return entity.getId();
    }

    @Override
    public ReturnBook returnBook(ReturnBookRequest request) {
        BookBorrowerEntity returnBook = bookBorrowerRepository.findNotReturnBookId(request.getBookId())
                .orElseThrow(() -> new BookBorrowerException("cannot find borrowed books"));
        returnBook.setActualReturnDate(LocalDateTime.now());

        LocalDate borrowDate = returnBook.getBorrowDate().toLocalDate();
        LocalDate returnDate = LocalDate.now();

        Long duration = ChronoUnit.DAYS.between(borrowDate, returnDate);

        ReturnBook response = new ReturnBook();
        response.setBorrowId(returnBook.getId());
        response.setBorrowDate(borrowDate);
        response.setReturnDate(returnDate);
        response.setDuration(duration);
        return response;
    }
}
