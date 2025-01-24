package com.teamso.flight_reservation_system.dto.request;

import lombok.Data;

@Data
public class SeatRequestDto {
    private String seatNumber;
    private Long flightId;
}
