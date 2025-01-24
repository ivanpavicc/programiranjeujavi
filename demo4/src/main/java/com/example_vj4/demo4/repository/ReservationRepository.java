package com.example_vj4.demo4.repository;

import com.example_vj4.demo4.model.Reservation;
import com.example_vj4.demo4.model.Member;
import com.example_vj4.demo4.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByMemberAndBook(Member member, Book book);
}
