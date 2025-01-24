package com.teamso.flight_reservation_system.dto.request;

import lombok.Data;

@Data
public class ReservationRequestDto {
    private Long userId;
    private Long seatId;
}
