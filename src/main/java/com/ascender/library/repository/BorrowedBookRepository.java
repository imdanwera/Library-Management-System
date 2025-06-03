package com.ascender.library.repository;

import com.ascender.library.entity.Book;
import com.ascender.library.entity.BorrowedBook;
import com.ascender.library.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
    Optional<BorrowedBook> findByBorrowerAndBook(Borrower borrower, Book book);

    Optional<BorrowedBook> findByBookIdAndReturnedFalse(Long bookId);

    boolean existsByBook(Book book);
}

