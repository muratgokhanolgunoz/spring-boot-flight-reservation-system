package com.teamso.flight_reservation_system.repository;

import com.teamso.flight_reservation_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
