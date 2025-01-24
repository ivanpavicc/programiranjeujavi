package com.example_vj4.demo4.service;

import com.example_vj4.demo4.model.Book;
import com.example_vj4.demo4.repository.BookRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.io.InputStream;
import java.util.List;

@Service
public class DataLoaderService {

    private final BookRepository bookRepository;

    @Autowired
    public DataLoaderService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void loadBooks() {
        try {
            // Učitavanje JSON datoteke
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("books.json");
            List<Book> books = objectMapper.readValue(inputStream, new TypeReference<List<Book>>() {});

            // Spremanje knjiga u bazu podataka
            bookRepository.saveAll(books);

            System.out.println("Podaci su uspješno učitani iz books.json u bazu podataka.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Došlo je do greške prilikom učitavanja podataka iz books.json.");
        }
    }
}
