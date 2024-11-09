package com.saiful.library.repository;

import com.saiful.library.entity.BookBorrowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookBorrowerRepository extends JpaRepository<BookBorrowerEntity, Long> {
    @Query("SELECT e FROM BookBorrowerEntity e WHERE e.book.id =:bookId AND e.actualReturnDate IS NULL")
    Optional<BookBorrowerEntity> findNotReturnBookId(Long bookId);
}
