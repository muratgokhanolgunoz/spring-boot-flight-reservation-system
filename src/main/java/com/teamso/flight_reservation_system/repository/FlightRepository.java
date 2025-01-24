package com.teamso.flight_reservation_system.repository;

import com.teamso.flight_reservation_system.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

}
