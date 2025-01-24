package com.teamso.flight_reservation_system.controller;

import com.teamso.flight_reservation_system.entity.Airport;
import com.teamso.flight_reservation_system.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/airport")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping
    public ResponseEntity<Airport> create(@RequestBody Airport airport) {
        Airport newAirport = airportService.create(airport);
        return ResponseEntity.ok(newAirport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> update(@PathVariable Long id, @RequestBody Airport airport) {
        Airport updatedAirport = airportService.update(id, airport);
        return ResponseEntity.ok(updatedAirport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        airportService.delete(id);
        return ResponseEntity.ok("Airport deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Airport>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(airportService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAll() {
        return ResponseEntity.ok(airportService.getAll());
    }
}
