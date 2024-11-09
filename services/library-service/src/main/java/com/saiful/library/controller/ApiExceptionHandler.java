package com.saiful.library.controller;

import com.saiful.library.exception.BookBorrowerException;
import com.saiful.library.exception.BookException;
import com.saiful.library.exception.BorrowerException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ProblemDetail borrowerException(BorrowerException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatus(ex.getHttpStatus().value());
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler
    public ProblemDetail bookException(BookException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatus(ex.getHttpStatus().value());
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler
    public ProblemDetail bookBorrowerException(BookBorrowerException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatus(ex.getHttpStatus().value());
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }
}
