package com.ascender.library.service.impl;

import com.ascender.library.entity.Book;
import com.ascender.library.entity.BorrowedBook;
import com.ascender.library.entity.Borrower;
import com.ascender.library.exception.BadRequestException;
import com.ascender.library.exception.ResourceNotFoundException;
import com.ascender.library.repository.BookRepository;
import com.ascender.library.repository.BorrowedBookRepository;
import com.ascender.library.repository.BorrowerRepository;
import com.ascender.library.service.LibraryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;
    private final BorrowedBookRepository borrowedBookRepository;

    @Transactional
    @Override
    public void borrowBook(Long borrowerId, Long bookId) {

        /*
            Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        if (book.isBorrowed()) {
            throw new IllegalStateException("Book is already borrowed");
        }

        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new EntityNotFoundException("Borrower not found"));

        book.setBorrowed(true);
        bookRepository.save(book);

        BorrowedBook borrowedBook = BorrowedBook.builder()
                .book(book)
                .borrower(borrower)
                .returned(false)
                .build();

        borrowedBookRepository.save(borrowedBook);
         */
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found"));

        log.info("checking book os bollowed or not");
        boolean isBorrowed = borrowedBookRepository.existsByBook(book);
        if (isBorrowed) {
            throw new BadRequestException("Book is already borrowed");
        }

        book.setBorrowed(true);
        bookRepository.save(book);

        BorrowedBook borrowedBook = BorrowedBook.builder()
                .book(book)
                .borrower(borrower)
                .returned(false)
                .build();

        borrowedBookRepository.save(borrowedBook);
    }


    @Transactional
    @Override
    public void returnBook(Long borrowerId, Long bookId) {
        BorrowedBook borrowedBook = borrowedBookRepository
                .findByBookIdAndReturnedFalse(bookId)
                .orElseThrow(() -> new EntityNotFoundException("No active borrowing found for book"));

        if (!borrowedBook.getBorrower().getId().equals(borrowerId)) {
            throw new IllegalArgumentException("This book was not borrowed by the given borrower");
        }

        borrowedBook.setReturned(true);

        borrowedBookRepository.save(borrowedBook);
        bookRepository.save(borrowedBook.getBook());
    }
}
