package com.ascender.library.service.impl;

import com.ascender.library.dto.BookDto;
import com.ascender.library.entity.Book;
import com.ascender.library.repository.BookRepository;
import com.ascender.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public BookDto registerBook(BookDto dto) {
        Book book = Book.builder()
                .isbn(dto.getIsbn())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .borrowed(false)
                .build();
        book = bookRepository.save(book);
        dto.setId(book.getId());
        dto.setBorrowed(false);
        return dto;
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> BookDto.builder()
                        .id(book.getId())
                        .isbn(book.getIsbn())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .borrowed(book.isBorrowed())
                        .build())
                .collect(Collectors.toList());
    }
}
