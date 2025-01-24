package com.teamso.flight_reservation_system.service;

import com.teamso.flight_reservation_system.dto.request.SeatRequestDto;
import com.teamso.flight_reservation_system.entity.Flight;
import com.teamso.flight_reservation_system.entity.Seat;
import com.teamso.flight_reservation_system.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final FlightService flightService;

    @Autowired
    public SeatService(SeatRepository seatRepository, FlightService flightService) {
        this.seatRepository = seatRepository;
        this.flightService = flightService;
    }

    @Transactional
    public Seat create(SeatRequestDto seatRequestDto) throws IllegalArgumentException {
        Flight flight = flightService.getById(seatRequestDto.getFlightId())
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        Seat seat = Seat.builder()
                .seatNumber(seatRequestDto.getSeatNumber())
                .isPurchased(false)
                .flight(flight)
                .build();

        return seatRepository.save(seat);
    }

    @Transactional
    public Seat update(Long seatId, SeatRequestDto seatRequestDto) throws IllegalArgumentException {
        Flight flight = flightService.getById(seatRequestDto.getFlightId())
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        seat.setSeatNumber(seatRequestDto.getSeatNumber());
        seat.setFlight(flight);

        return seatRepository.save(seat);
    }

    @Transactional
    public Seat setSeatAsPurchased(Long id) throws IllegalArgumentException {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        if (seat.isPurchased()) {
            throw new IllegalStateException("Seat is already purchased");
        }

        seat.setPurchased(true);
        return seatRepository.save(seat);
    }

    @Transactional
    public void delete(Long seatId) throws IllegalArgumentException, IllegalStateException {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        if (seat.isPurchased()) {
            throw new IllegalStateException("Seat has been purchased, therefore it cannot be deleted");
        }

        seatRepository.delete(seat);
    }

    public Optional<Seat> getById(Long id) {
        return seatRepository.findById(id);
    }
}
