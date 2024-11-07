package com.saiful.library.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterBorrower {

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;
}
