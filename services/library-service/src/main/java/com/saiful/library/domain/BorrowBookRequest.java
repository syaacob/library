package com.saiful.library.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowBookRequest {
    @NotNull
    private Long borrowerId;
    @NotNull
    private Long bookId;
}
