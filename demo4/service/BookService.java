package com.example_vj4.demo4.service;

import com.example_vj4.demo4.BookNotFoundException;
import com.example_vj4.demo4.InvalidRequestException;
import com.example_vj4.demo4.model.Book;
import com.example_vj4.demo4.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // Metoda za dohvat knjiga s filtriranjem, pretraživanjem, paginacijom i sortiranjem
    public Page<Book> getBooks(String title, String author, String genre, Integer publicationYear, Pageable pageable) {
        if (title != null && author != null) {
            return bookRepository.findByTitleContainingAndAuthorContaining(title, author, pageable);
        } else if (title != null) {
            return bookRepository.findByTitleContaining(title, pageable);
        } else if (author != null) {
            return bookRepository.findByAuthorContaining(author, pageable);
        } else if (genre != null && publicationYear != null) {
            return bookRepository.findByGenreAndPublicationYear(genre, publicationYear, pageable);
        }
        return bookRepository.findAll(pageable);  // Ako nisu postavljeni parametri, vratit ćemo sve knjige
    }

    // Metoda za spremanje nove knjige
    public Book saveBook(Book book) {
        if (book == null || book.getTitle() == null || book.getAuthor() == null) {
            throw new InvalidRequestException("Invalid book data. Title and author are required.");
        }
        return bookRepository.save(book);
    }

    // Metoda za dohvat knjige prema ID-u
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));  // Ako knjiga nije pronađena, bacamo iznimku
    }

    // Metoda za brisanje knjige prema ID-u
    public void deleteBook(Long id) {
        bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));  // Provjera postoji li knjiga prije brisanja
        bookRepository.deleteById(id);
    }
}