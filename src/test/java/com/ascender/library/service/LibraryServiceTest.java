package com.ascender.library.service;

import com.ascender.library.entity.Book;
import com.ascender.library.entity.Borrower;
import com.ascender.library.entity.BorrowedBook;
import com.ascender.library.exception.BadRequestException;
import com.ascender.library.repository.BookRepository;
import com.ascender.library.repository.BorrowerRepository;
import com.ascender.library.repository.BorrowedBookRepository;
import com.ascender.library.service.impl.LibraryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class LibraryServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowerRepository borrowerRepository;

    @Mock
    private BorrowedBookRepository borrowedBookRepository;

    @InjectMocks
    private LibraryServiceImpl libraryService;

    private Borrower borrower;
    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        borrower = Borrower.builder().id(1L).name("Alice").email("a@a.com").build();
        book = Book.builder().id(10L).isbn("XYZ").title("T").author("A").borrowed(false).build();
    }

    @Test
    void testBorrowBookSuccess() {
        when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrower));
        when(bookRepository.findById(10L)).thenReturn(Optional.of(book));

        libraryService.borrowBook(1L, 10L);

        assertThat(book.isBorrowed()).isTrue();
        verify(borrowedBookRepository).save(any(BorrowedBook.class));
    }

    @Test
    void testBorrowBookAlreadyBorrowed() {
        book.setBorrowed(true);
        when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrower));
        when(bookRepository.findById(10L)).thenReturn(Optional.of(book));

        assertThatThrownBy(() -> libraryService.borrowBook(1L, 10L))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("already borrowed");
    }

    @Test
    void testReturnBookSuccess() {
        book.setBorrowed(true);
        when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrower));
        when(bookRepository.findById(10L)).thenReturn(Optional.of(book));

        BorrowedBook borrowedBook = BorrowedBook.builder().id(1L).book(book).borrower(borrower).build();
        when(borrowedBookRepository.findByBorrowerAndBook(borrower, book)).thenReturn(Optional.of(borrowedBook));

        libraryService.returnBook(1L, 10L);

        assertThat(book.isBorrowed()).isFalse();
        verify(borrowedBookRepository).delete(borrowedBook);
    }
}
