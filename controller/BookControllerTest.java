package com.example_vj4.demo4.controller;

import com.example_vj4.demo4.model.Book;
import com.example_vj4.demo4.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void testGetBooksWithFilters() throws Exception {
        // Mock podataka
        Book book = new Book(1L, "1984", "George Orwell", "Dystopian", 1949);
        given(bookService.getBooks("1984", "George Orwell", "Dystopian", 1949, null)).willReturn(Page.of(book));

        // Testiranje GET zahtjeva
        mockMvc.perform(get("/api/books")
                        .param("title", "1984")
                        .param("author", "George Orwell")
                        .param("genre", "Dystopian")
                        .param("publicationYear", "1949"))
                .andExpect(status().isOk());
    }
}
