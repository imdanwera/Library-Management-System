package com.ascender.library.service;

import com.ascender.library.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto registerBook(BookDto bookDto);
    List<BookDto> getAllBooks();
}
