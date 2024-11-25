package com.java.springBoot.app.Model;

import javax.persistence.*;
import lombok.*;
import javax.validation.constraints.*;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 255, message = "Author name cannot exceed 255 characters")
    private String author;

    @NotNull(message = "Publication year is required")
    @Min(value = 1450, message = "Publication year must be after 1450")
    @Max(value = 2024, message = "Publication year cannot be in the future")
    private Integer publicationYear;

    @NotBlank(message = "ISBN is required")
    @Pattern(
            regexp = "^(97(8|9))?\\d{9}(\\d|X)$",
            message = "Invalid ISBN format. Must be a valid ISBN-10 or ISBN-13"
    )
    private String isbn;
}
