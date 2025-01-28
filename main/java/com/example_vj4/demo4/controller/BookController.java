package com.example_vj4.demo4.controller;

import com.example_vj4.demo4.model.Book;
import com.example_vj4.demo4.service.BookService;
import exception.BookNotFoundException;
import exception.InvalidRequestException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    // Metoda za dohvat svih knjiga s filtriranjem, paginacijom i sortiranjem
    @GetMapping
    public Page<Book> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer publicationYear,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title,asc") String sort) {

        // Razdvajanje parametra sort za sortiranje (npr. "title,asc")
        String[] sortParams = sort.split(",");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortParams[0]).ascending());

        // Pozivanje servisa za pretragu i filtriranje
        return bookService.getBooks(title, author, genre, publicationYear, pageable);
    }

    // Metoda za kreiranje nove knjige
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);  // Status 201
    }

    // Metoda za ažuriranje knjige prema ID-u
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook); // Vraća 200 OK sa ažuriranom knjigom
    }

    // Metoda za dohvat knjige prema ID-u
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // Metoda za brisanje knjige prema ID-u
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();  // Vraća 204 No Content
    }
}
