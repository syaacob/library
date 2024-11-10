package com.saiful.library.controller;

import com.saiful.library.exception.BookBorrowerException;
import com.saiful.library.exception.BookException;
import com.saiful.library.exception.BorrowerException;
import com.saiful.library.exception.LibraryExceptionHandler;
import org.springframework.http.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BorrowerException.class})
    public ProblemDetail borrowerException(BorrowerException ex){

        ProblemDetail problemDetail = handleLibraryApiErrorResponse(ex);
        return problemDetail;
    }

    @ExceptionHandler({BookException.class})
    public ProblemDetail bookException(BookException ex){
        ProblemDetail problemDetail = handleLibraryApiErrorResponse(ex);
        return problemDetail;
    }

    @ExceptionHandler({BookBorrowerException.class})
    public ProblemDetail bookBorrowerException(BookBorrowerException ex){
        ProblemDetail problemDetail = handleLibraryApiErrorResponse(ex);
        return problemDetail;
    }

    private ProblemDetail handleLibraryApiErrorResponse(LibraryExceptionHandler handler){
        ProblemDetail problemDetail = ProblemDetail.forStatus(handler.getHttpStatus().value());
        problemDetail.setDetail(handler.getErrorMessage());
        problemDetail.setType(URI.create("http://localhost:8080/errors/bad-request"));
        problemDetail.setTitle("bad request");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        ProblemDetail problemDetail = handleValidationException(ex);
        return ResponseEntity.status(status.value()).body(problemDetail);
    }

    private ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
        String details = getErrorsDetails(ex);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatusCode(), details);
        problemDetail.setType(URI.create("http://localhost:8080/errors/bad-request"));
        problemDetail.setTitle("bad request");
        problemDetail.setInstance(ex.getBody().getInstance());
        problemDetail.setProperty("timestamp", Instant.now()); // adding more data using the Map properties of the ProblemDetail
        return problemDetail;
    }
    private String getErrorsDetails(MethodArgumentNotValidException ex) {
        return Optional.ofNullable(ex.getDetailMessageArguments())
                .map(args -> Arrays.stream(args)
                        .filter(msg -> !ObjectUtils.isEmpty(msg))
                        .reduce("please make sure to provide a valid request, ", (a, b) -> a + " " + b)
                )
                .orElse("").toString();
    }
}
