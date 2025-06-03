package com.ascender.library.repository;

import com.ascender.library.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testSaveAndFindByIsbn() {
        Book book = Book.builder()
                .isbn("ISBN123")
                .title("Effective Java")
                .author("Joshua Bloch")
                .build();

        bookRepository.save(book);

        List<Book> found = bookRepository.findByIsbn("ISBN123");

        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getTitle()).isEqualTo("Effective Java");
    }
}
