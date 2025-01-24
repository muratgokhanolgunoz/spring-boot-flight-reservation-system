package com.teamso.flight_reservation_system.service;

import com.teamso.flight_reservation_system.dto.ReservationDto;
import com.teamso.flight_reservation_system.entity.Flight;
import com.teamso.flight_reservation_system.entity.Reservation;
import com.teamso.flight_reservation_system.entity.Seat;
import com.teamso.flight_reservation_system.entity.User;
import com.teamso.flight_reservation_system.repository.ReservationRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatService seatService;
    private final FlightService flightService;
    private final UserService userService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, SeatService seatService, FlightService flightService, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.seatService = seatService;
        this.flightService = flightService;
        this.userService = userService;
    }

    @Transactional
    public Reservation create(ReservationDto reservationDto) throws IllegalArgumentException, IllegalStateException {
        Flight flight = flightService.getById(reservationDto.getFlightId())
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        Seat seat = seatService.getById(reservationDto.getSeatId())
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        if (!seat.getFlight().equals(flight)) {
            throw new IllegalArgumentException("Seat does not belong to the specified flight");
        }

        if (seat.isPurchased()) {
            throw new IllegalStateException("Seat is already reserved");
        }

        User user = userService.getById(reservationDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        try {
            seat = seatService.updateIsPurchased(seat.getId(), true);

            Reservation reservation = Reservation.builder()
                    .user(user)
                    .seat(seat)
                    .flight(flight)
                    .build();

            return reservationRepository.save(reservation);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new IllegalStateException("Conflict occurred while reserving the seat. Please try again.", e);
        }
    }
}

