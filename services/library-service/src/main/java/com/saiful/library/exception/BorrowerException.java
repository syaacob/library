package com.saiful.library.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BorrowerException extends RuntimeException {
    HttpStatus httpStatus;
    public BorrowerException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
