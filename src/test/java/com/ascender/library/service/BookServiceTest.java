package com.ascender.library.service;

import com.ascender.library.dto.BookDto;
import com.ascender.library.entity.Book;
import com.ascender.library.repository.BookRepository;
import com.ascender.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterBook() {
        BookDto dto = BookDto.builder().isbn("111").title("DDD").author("Evans").build();
        Book book = Book.builder().id(1L).isbn("111").title("DDD").author("Evans").build();
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookDto result = bookService.registerBook(dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void testGetAllBooks() {
        Book book = Book.builder().id(1L).isbn("123").title("Test Book").author("Author").build();
        when(bookRepository.findAll()).thenReturn(List.of(book));

        List<BookDto> result = bookService.getAllBooks();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Book");
    }
}
