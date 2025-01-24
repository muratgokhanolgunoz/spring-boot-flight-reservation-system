package com.teamso.flight_reservation_system.controller;

import com.teamso.flight_reservation_system.dto.FlightDto;
import com.teamso.flight_reservation_system.dto.SeatDto;
import com.teamso.flight_reservation_system.entity.Flight;
import com.teamso.flight_reservation_system.entity.Seat;
import com.teamso.flight_reservation_system.service.FlightService;
import com.teamso.flight_reservation_system.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;
    private final SeatService seatService;

    @Autowired
    public FlightController(FlightService flightService, SeatService seatService) {
        this.flightService = flightService;
        this.seatService = seatService;
    }

    @PostMapping
    public ResponseEntity<Flight> create(@RequestBody FlightDto flightDto) {
        Flight flight = flightService.create(flightDto);
        return ResponseEntity.ok(flight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> update(@PathVariable Long id, @RequestBody FlightDto flightDto) {
        Flight updatedFlight = flightService.update(id, flightDto);
        return ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        flightService.delete(id);
        return ResponseEntity.ok("Flight deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Flight>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAll() {
        return ResponseEntity.ok(flightService.getAll());
    }

    @PostMapping("/{flightId}/seat")
    public ResponseEntity<Seat> createSeat(@PathVariable Long flightId, @RequestBody SeatDto seatDto) {
        Seat seat = seatService.create(flightId, seatDto);
        return ResponseEntity.ok(seat);
    }

    @PutMapping("/{flightId}/seat/{seatId}")
    public ResponseEntity<Seat> updateSeat(@PathVariable Long flightId, @PathVariable Long seatId, @RequestBody SeatDto seatDto) {
        Seat updatedSeat = seatService.update(flightId, seatId, seatDto);
        return ResponseEntity.ok(updatedSeat);
    }

    @DeleteMapping("/{flightId}/seat/{seatId}")
    public ResponseEntity<String> deleteSeat(@PathVariable Long flightId, @PathVariable Long seatId) {
        seatService.delete(flightId, seatId);
        return ResponseEntity.ok("Seat deleted successfully");
    }
}
