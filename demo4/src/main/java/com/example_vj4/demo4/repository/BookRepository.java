package com.example_vj4.demo4.repository;

import com.example_vj4.demo4.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Ove metode omogućuju filtriranje i paginaciju
    Page<Book> findByTitleContainingAndAuthorContaining(String title, String author, Pageable pageable);
    Page<Book> findByTitleContaining(String title, Pageable pageable);
    Page<Book> findByAuthorContaining(String author, Pageable pageable);
    Page<Book> findByGenreAndPublicationYear(String genre, Integer publicationYear, Pageable pageable);
}
