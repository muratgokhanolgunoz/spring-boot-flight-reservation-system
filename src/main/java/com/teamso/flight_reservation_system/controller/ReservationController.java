package com.teamso.flight_reservation_system.controller;

import com.teamso.flight_reservation_system.dto.request.ReservationRequestDto;
import com.teamso.flight_reservation_system.entity.Reservation;
import com.teamso.flight_reservation_system.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody ReservationRequestDto reservationRequestDto) {
        Reservation reservation = reservationService.create(reservationRequestDto);
        return ResponseEntity.ok(reservation);
    }
}
