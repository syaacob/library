package com.saiful.library.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.ISBN;

@Getter
@Setter
public class RegisterBook {

    @NotNull
    private String title;

    @NotNull
    @ISBN(message = "invalid isbn")
    private String isbn;

    @NotNull
    private String author;
}
