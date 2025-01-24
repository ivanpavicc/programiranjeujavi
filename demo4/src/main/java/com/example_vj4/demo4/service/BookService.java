package com.example_vj4.demo4.service;

import com.example_vj4.demo4.model.Book;
import com.example_vj4.demo4.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Dohvaća sve knjige
    public List<Book> getAllBooks() {
        return bookRepository.findAll();  // Vraća sve knjige
    }

    // Dohvaća knjigu prema ID-u
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    // Sprema novu knjigu
    public Book saveBook(Book book) {
        return bookRepository.save(book);  // Sprema knjigu u bazu
    }

    // Briše knjigu prema ID-u
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);  // Briše knjigu prema ID-u
    }

    // Dohvaća knjige s filtriranjem i paginacijom
    public Page<Book> getBooks(String title, String author, String genre, Integer publicationYear, Pageable pageable) {
        if (title != null && author != null) {
            return bookRepository.findByTitleContainingAndAuthorContaining(title, author, pageable);  // Filtrira prema title i author
        } else if (title != null) {
            return bookRepository.findByTitleContaining(title, pageable);  // Filtrira prema title
        } else if (author != null) {
            return bookRepository.findByAuthorContaining(author, pageable);  // Filtrira prema author
        } else if (genre != null && publicationYear != null) {
            return bookRepository.findByGenreAndPublicationYear(genre, publicationYear, pageable);  // Filtrira prema genre i publicationYear
        } else {
            return bookRepository.findAll(pageable);  // Vraća sve knjige s paginacijom
        }
    }

    // Dohvaća knjige s osnovnom paginacijom
    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);  // Vraća sve knjige s paginacijom
    }
}
