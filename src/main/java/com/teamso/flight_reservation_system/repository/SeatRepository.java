package com.teamso.flight_reservation_system.repository;

import com.teamso.flight_reservation_system.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {

}
