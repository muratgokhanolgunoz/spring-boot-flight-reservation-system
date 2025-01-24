package com.teamso.flight_reservation_system.service;

import com.teamso.flight_reservation_system.dto.request.ReservationRequestDto;
import com.teamso.flight_reservation_system.entity.Reservation;
import com.teamso.flight_reservation_system.entity.Seat;
import com.teamso.flight_reservation_system.entity.User;
import com.teamso.flight_reservation_system.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatService seatService;
    private final UserService userService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, SeatService seatService, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.seatService = seatService;
        this.userService = userService;
    }

    @Transactional
    public synchronized Reservation create(ReservationRequestDto reservationRequestDto) throws IllegalArgumentException, IllegalStateException {
        try {
            Seat seat = seatService.setSeatAsPurchased(reservationRequestDto.getSeatId());

            User user = userService.getById(reservationRequestDto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            Reservation reservation = Reservation.builder()
                    .user(user)
                    .seat(seat)
                    .build();

            return reservationRepository.save(reservation);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new IllegalStateException("Transaction cannot be processed. Seat is already purchased by another user.", e);
        }
    }
}

