package com.ascender.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "BookDto", description = "Book Details")
public class BookDto {
    private Long id;

    @NotBlank(message = "ISBN must not be empty")
    private String isbn;

    @NotBlank(message = "Title must not be empty")
    private String title;

    @NotBlank(message = "Author must not be empty")
    private String author;

    private boolean borrowed;
}
