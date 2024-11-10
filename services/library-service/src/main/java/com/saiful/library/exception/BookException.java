package com.saiful.library.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BookException extends RuntimeException implements LibraryExceptionHandler {
    private HttpStatus httpStatus;
    public BookException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return super.getMessage();
    }

    @Override
    public String getInstance() {
        return "book";
    }
}
