package com.saiful.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_borrower")
@Getter
@Setter
public class BookBorrowerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_borrower_seq")
    @SequenceGenerator(name = "book_borrower_seq", sequenceName = "book_borrower_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private BorrowerEntity borrower;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @Column(name = "borrow_date")
    private LocalDateTime borrowDate;

    @Column(name = "expected_return_date")
    private LocalDateTime expectedReturnDate;

    @Column(name = "actual_return_date")
    private LocalDateTime actualReturnDate;

}
