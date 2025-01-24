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
}
