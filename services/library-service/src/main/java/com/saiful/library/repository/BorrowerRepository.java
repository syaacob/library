package com.saiful.library.repository;

import com.saiful.library.entity.BorrowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BorrowerRepository extends JpaRepository<BorrowerEntity, Long> {
    @Query("select case when count(e) > 0  then true else false end from BorrowerEntity e where e.email = :email")
    Boolean isEmailExist(@Param("email") String email);
}
