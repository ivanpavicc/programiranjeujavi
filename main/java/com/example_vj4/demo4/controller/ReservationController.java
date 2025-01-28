package com.example_vj4.demo4.controller;


import com.example_vj4.demo4.model.Reservation;
import com.example_vj4.demo4.model.Book;
import com.example_vj4.demo4.model.Member;
import com.example_vj4.demo4.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

;


@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public Reservation createReservation(@RequestParam Long memberId, @RequestParam Long bookId) {
        return reservationService.createReservation(memberId, bookId);
    }
}

