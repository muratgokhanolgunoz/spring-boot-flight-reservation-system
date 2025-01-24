package com.teamso.flight_reservation_system.dto;

import lombok.Data;

@Data
public class FlightDto {
    private String name;
    private String description;
    private double price;
    private Long departureAirportId;
    private Long arrivalAirportId;
}
