package com.example_vj4.demo4.service;

import com.example_vj4.demo4.model.Book;
import com.example_vj4.demo4.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookServiceTest {

    private BookService bookService;
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void testGetBooksWithFilters() {
        // Mock podataka
        Book book = new Book(1L, "1984", "George Orwell", "Dystopian", 1949);
        List<Book> bookList = List.of(book);
        Page<Book> bookPage = new PageImpl<>(bookList);
        given(bookRepository.findByTitleContainingAndAuthorContaining("1984", "George Orwell", Pageable.unpaged())).willReturn(bookPage);

        // Pozivanje metode i provjera
        Page<Book> books = bookService.getBooks("1984", "George Orwell", "Dystopian", 1949, Pageable.unpaged());
        assertEquals(1, books.getContent().size());
        assertEquals("1984", books.getContent().get(0).getTitle());
    }

    @Test
    void testSaveBook() {
        Book book = new Book(1L, "1984", "George Orwell", "Dystopian", 1949);
        given(bookRepository.save(book)).willReturn(book);

        Book savedBook = bookService.saveBook(book);
        assertEquals("1984", savedBook.getTitle());
    }

    @Test
    void testDeleteBook() {
        long bookId = 1L;
        Mockito.doNothing().when(bookRepository).deleteById(bookId);

        bookService.deleteBook(bookId);
        Mockito.verify(bookRepository, Mockito.times(1)).deleteById(bookId);
    }
}
