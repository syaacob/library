package com.saiful.library.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnBookRequest {
    @NotNull
    private Long bookId;
}
