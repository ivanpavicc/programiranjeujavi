package com.example_vj4.demo4.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testBookConstructorAndGetters() {
        Book book = new Book(1L, "1984", "George Orwell", "Dystopian", 1949);

        // Testiraj konstruktore i gettere
        assertEquals(1L, book.getId());
        assertEquals("1984", book.getTitle());
        assertEquals("George Orwell", book.getAuthor());
        assertEquals("Dystopian", book.getGenre());
        assertEquals(1949, book.getPublicationYear());
    }

    @Test
    void testSetters() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("1984");
        book.setAuthor("George Orwell");
        book.setGenre("Dystopian");
        book.setPublicationYear(1949);

        // Testiraj settere
        assertEquals(1L, book.getId());
        assertEquals("1984", book.getTitle());
        assertEquals("George Orwell", book.getAuthor());
        assertEquals("Dystopian", book.getGenre());
        assertEquals(1949, book.getPublicationYear());
    }
}