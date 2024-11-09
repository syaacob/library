package com.saiful.library.repository;

import com.saiful.library.entity.BookBorrowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookBorrowerRepository extends JpaRepository<BookBorrowerEntity, Long> {
}
