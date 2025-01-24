package com.teamso.flight_reservation_system.controller;

import com.teamso.flight_reservation_system.dto.ReservationDto;
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
    public ResponseEntity<Reservation> create(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationService.create(reservationDto);
        return ResponseEntity.ok(reservation);
    }
}
