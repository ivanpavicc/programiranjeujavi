package com.example_vj4.demo4.repository;
import com.example_vj4.demo4.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
