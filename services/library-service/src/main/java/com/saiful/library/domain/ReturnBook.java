package com.saiful.library.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReturnBook {
    private Long borrowId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Long duration;
}
