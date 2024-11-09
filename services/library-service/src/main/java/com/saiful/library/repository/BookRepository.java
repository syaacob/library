package com.saiful.library.repository;

import com.saiful.library.entity.BookEntity;
import com.saiful.library.entity.BookStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Page<BookEntity> findAllByIsbn(String isbn, Pageable pageable);
    Page<BookEntity> findAllByTitleContains(String title, Pageable pageable);
    Page<BookEntity> findAllByAuthorContains(String author, PageRequest pageable);

    @Modifying
    @Query("UPDATE BookEntity SET bookStatus = :bookStatus WHERE id = :id")
    int updateStatus(Long id, BookStatus bookStatus);
}
