package com.teamso.flight_reservation_system.service;

import com.teamso.flight_reservation_system.entity.Airport;
import com.teamso.flight_reservation_system.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Transactional
    public Airport create(Airport airport) {
        return airportRepository.save(airport);
    }

    @Transactional
    public Airport update(Long id, Airport airport) throws IllegalArgumentException {
        Airport existingAirport = airportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airport not found"));

        existingAirport.setName(airport.getName());
        existingAirport.setCode(airport.getCode());
        existingAirport.setCity(airport.getCity());
        existingAirport.setCountry(airport.getCountry());

        return airportRepository.save(existingAirport);
    }

    @Transactional
    public void delete(Long id) {
        airportRepository.deleteById(id);
    }

    public Optional<Airport> getById(Long id) {
        return airportRepository.findById(id);
    }

    public List<Airport> getAll() {
        return airportRepository.findAll();
    }
}
