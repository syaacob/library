package com.saiful.library.repository;

import com.saiful.library.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Page<BookEntity> findAllByIsbn(String isbn, Pageable pageable);
    Page<BookEntity> findAllByTitleContains(String title, Pageable pageable);
    Page<BookEntity> findAllByAuthorContains(String author, PageRequest pageable);
}
