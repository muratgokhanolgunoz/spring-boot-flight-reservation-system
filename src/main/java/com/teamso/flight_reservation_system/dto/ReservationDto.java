package com.teamso.flight_reservation_system.dto;

import lombok.Data;

@Data
public class ReservationDto {
    private Long userId;
    private Long flightId;
    private Long seatId;
}
