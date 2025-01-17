package com.example_vj4.demo4.controller;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public Reservation createReservation(
            @RequestParam Long memberId,
            @RequestParam(name = "books") List<Long> bookIds) {
        return reservationService.createReservation(memberId, bookIds);
    }


    @PutMapping("/fulfill") // Pogrešan URL
    public Reservation fulfillReservation(@RequestBody Long id) {
        return reservationService.fulfillReservation(id);
    }
}
