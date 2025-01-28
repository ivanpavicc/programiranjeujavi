package com.example_vj4.demo4.controller;

import com.example_vj4.demo4.model.Book;
import com.example_vj4.demo4.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class BookControllerTest {

    private MockMvc mockMvc;

    private BookService bookService;

    private BookController bookController;

    @BeforeEach
    void setUp() {
        // Kreiraj mock za BookService
        bookService = Mockito.mock(BookService.class);
        bookController = new BookController(bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void testGetBooksWithFilters() throws Exception {
        // Mock podataka
        Book book = new Book(1L, "1984", "George Orwell", "Dystopian", 1949);
        Page<Book> books = new PageImpl<>(List.of(book));
        given(bookService.getBooks("1984", "George Orwell", "Dystopian", 1949, null)).willReturn(books);

        // Testiranje GET zahtjeva
        mockMvc.perform(get("/api/books")
                        .param("title", "1984")
                        .param("author", "George Orwell")
                        .param("genre", "Dystopian")
                        .param("publicationYear", "1949")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateBook() throws Exception {
        Book newBook = new Book(1L, "New Book", "Some Author", "Fiction", 2023);
        given(bookService.saveBook(Mockito.any(Book.class))).willReturn(newBook);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Book\",\"author\":\"Some Author\",\"genre\":\"Fiction\",\"publicationYear\":2023}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()) // Provjera statusa 201
                .andExpect(jsonPath("$.title").value("New Book"));
    }

    @Test
    void testDeleteBook() throws Exception {
        long bookId = 1L;

        mockMvc.perform(delete("/api/books/{id}", bookId))
                .andExpect(status().isNoContent()); // Provjera statusa 204
    }
}
