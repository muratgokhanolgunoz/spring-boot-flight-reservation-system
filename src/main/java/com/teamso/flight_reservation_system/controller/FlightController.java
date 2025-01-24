package com.teamso.flight_reservation_system.controller;

import com.teamso.flight_reservation_system.dto.request.FlightRequestDto;
import com.teamso.flight_reservation_system.entity.Flight;
import com.teamso.flight_reservation_system.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<Flight> create(@RequestBody FlightRequestDto flightRequestDto) {
        Flight flight = flightService.create(flightRequestDto);
        return ResponseEntity.ok(flight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> update(@PathVariable Long id, @RequestBody FlightRequestDto flightRequestDto) {
        Flight updatedFlight = flightService.update(id, flightRequestDto);
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
}
