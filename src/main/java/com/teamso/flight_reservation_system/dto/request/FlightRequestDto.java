package com.teamso.flight_reservation_system.dto.request;

import lombok.Data;

@Data
public class FlightRequestDto {
    private String name;
    private String description;
    private double price;
    private Long departureAirportId;
    private Long arrivalAirportId;
}
