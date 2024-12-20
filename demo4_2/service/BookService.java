package com.example_vj4.demo4.service;

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
        return bookRepository.findAll(pageable);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
