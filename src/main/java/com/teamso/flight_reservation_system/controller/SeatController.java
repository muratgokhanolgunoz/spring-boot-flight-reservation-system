package com.teamso.flight_reservation_system.controller;

import com.teamso.flight_reservation_system.dto.request.SeatRequestDto;
import com.teamso.flight_reservation_system.entity.Seat;
import com.teamso.flight_reservation_system.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seat")
public class SeatController {
    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping
    public ResponseEntity<Seat> create(@RequestBody SeatRequestDto seatRequestDto) {
        Seat seat = seatService.create(seatRequestDto);
        return ResponseEntity.ok(seat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seat> update(@PathVariable Long id, @RequestBody SeatRequestDto seatRequestDto) {
        Seat updatedSeat = seatService.update(id, seatRequestDto);
        return ResponseEntity.ok(updatedSeat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        seatService.delete(id);
        return ResponseEntity.ok("Seat deleted successfully");
    }
}
