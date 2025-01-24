package com.teamso.flight_reservation_system.repository;

import com.teamso.flight_reservation_system.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
