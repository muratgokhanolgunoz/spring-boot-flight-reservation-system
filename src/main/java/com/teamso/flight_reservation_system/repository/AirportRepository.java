package com.teamso.flight_reservation_system.repository;

import com.teamso.flight_reservation_system.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

}
