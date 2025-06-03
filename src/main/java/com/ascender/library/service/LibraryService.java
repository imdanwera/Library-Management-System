package com.ascender.library.service;

public interface LibraryService {
    void borrowBook(Long borrowerId, Long bookId);
    void returnBook(Long borrowerId, Long bookId);
}
