package com.teamso.flight_reservation_system.dto;

import lombok.Data;

@Data
public class AirportDto {
    private String name;
    private String code;
    private String city;
    private String country;
}
