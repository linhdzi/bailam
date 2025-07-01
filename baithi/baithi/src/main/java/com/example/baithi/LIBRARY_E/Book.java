package com.example.baithi.LIBRARY_E;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Author cannot be empty")
    private String author;

    @Min(value = 1500, message = "Published year must be no earlier than 1500")
    private int publishedYear;

    @Min(value = 0, message = "Copies available must be >= 0")
    private int copiesAvailable;
}

