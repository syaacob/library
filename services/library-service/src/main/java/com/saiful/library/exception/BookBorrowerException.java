package com.saiful.library.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BookBorrowerException extends RuntimeException {
    private HttpStatus httpStatus;
    public BookBorrowerException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
