package com.teamso.flight_reservation_system.service;

import com.teamso.flight_reservation_system.dto.SeatDto;
import com.teamso.flight_reservation_system.entity.Flight;
import com.teamso.flight_reservation_system.entity.Seat;
import com.teamso.flight_reservation_system.repository.SeatRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;

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
    public Seat create(Long flightId, SeatDto seatDto) throws IllegalArgumentException {
        Flight flight = flightService.getById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        Seat seat = Seat.builder()
                .seatNumber(seatDto.getSeatNumber())
                .isPurchased(false)
                .flight(flight)
                .build();

        return seatRepository.save(seat);
    }

    @Transactional
    public Seat update(Long flightId, Long seatId, SeatDto seatDto) throws IllegalArgumentException {
        Flight flight = flightService.getById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        if (!seat.getFlight().equals(flight)) {
            throw new IllegalArgumentException("Seat does not belong to the specified flight");
        }

        seat.setSeatNumber(seatDto.getSeatNumber());

        return seatRepository.save(seat);
    }

    @Transactional
    public Seat updateIsPurchased(Long id, boolean isPurchased) throws IllegalArgumentException {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        seat.setPurchased(isPurchased);
        return seatRepository.save(seat);
    }

    @Transactional
    public void delete(Long flightId, Long seatId) throws IllegalArgumentException, IllegalStateException {
        Flight flight = flightService.getById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        if (!seat.getFlight().equals(flight)) {
            throw new IllegalArgumentException("Seat does not belong to the specified flight");
        }

        if(seat.isPurchased()) {
            throw new IllegalStateException("Seat has been purchased, therefore it cannot be deleted");
        }

        seatRepository.delete(seat);
    }

    public Optional<Seat> getById(Long id) {
        return seatRepository.findById(id);
    }
}
