package com.teamso.flight_reservation_system.service;

import com.teamso.flight_reservation_system.dto.FlightDto;
import com.teamso.flight_reservation_system.entity.Airport;
import com.teamso.flight_reservation_system.entity.Flight;
import com.teamso.flight_reservation_system.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportService airportService;

    @Autowired
    public FlightService(FlightRepository flightRepository, AirportService airportService) {
        this.flightRepository = flightRepository;
        this.airportService = airportService;
    }

    @Transactional
    public Flight create(FlightDto flightDto) throws IllegalArgumentException {
        Airport departureAirport = airportService.getById(flightDto.getDepartureAirportId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid departure airport ID"));
        Airport arrivalAirport = airportService.getById(flightDto.getArrivalAirportId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid arrival airport ID"));

        Flight flight = Flight.builder()
                .name(flightDto.getName())
                .description(flightDto.getDescription())
                .price(flightDto.getPrice())
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .build();

        return flightRepository.save(flight);
    }

    @Transactional
    public Flight update(Long id, FlightDto flightDto) throws IllegalArgumentException {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        Airport departureAirport = airportService.getById(flightDto.getDepartureAirportId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid departure airport ID"));
        Airport arrivalAirport = airportService.getById(flightDto.getArrivalAirportId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid arrival airport ID"));

        flight.setName(flightDto.getName());
        flight.setDescription(flightDto.getDescription());
        flight.setPrice(flightDto.getPrice());
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);

        return flightRepository.save(flight);
    }

    @Transactional
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }

    public Optional<Flight> getById(Long id) {
        return flightRepository.findById(id);
    }

    public List<Flight> getAll() {
        return flightRepository.findAll();
    }
}
