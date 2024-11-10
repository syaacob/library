package com.saiful.library.exception;

import org.springframework.http.HttpStatus;

public interface LibraryExceptionHandler {

    HttpStatus getHttpStatus();
    String getErrorMessage();
    String getInstance();
}
