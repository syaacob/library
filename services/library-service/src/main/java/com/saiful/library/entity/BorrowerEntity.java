package com.saiful.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "borrower")
@Getter
@Setter
public class BorrowerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrower_seq")
    @SequenceGenerator(name = "borrower_seq", sequenceName = "borrower_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String email;
}
