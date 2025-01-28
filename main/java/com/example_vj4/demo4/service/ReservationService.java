package com.example_vj4.demo4.service;

import com.example_vj4.demo4.model.Reservation;
import com.example_vj4.demo4.model.Book;
import com.example_vj4.demo4.model.Member;
import com.example_vj4.demo4.repository.ReservationRepository;
import com.example_vj4.demo4.repository.BookRepository;
import com.example_vj4.demo4.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public Reservation createReservation(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        // Provjera postoji li već rezervacija za tu knjigu i člana
        if (reservationRepository.existsByMemberAndBook(member, book)) {
            throw new IllegalArgumentException("Reservation already exists for this book and member");
        }

        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservation.setBook(book);
        reservation.setFulfilled(false);

        return reservationRepository.save(reservation);
    }
}