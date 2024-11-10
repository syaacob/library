package com.saiful.library.converter;

import com.saiful.library.domain.Borrower;
import com.saiful.library.entity.BorrowerEntity;

public class BorrowerConverter {
    public static Borrower convert(BorrowerEntity entity) {
        Borrower borrower = new Borrower();
        borrower.setId(entity.getId());
        borrower.setName(entity.getName());
        borrower.setEmail(entity.getEmail());

        return borrower;
    }
}
