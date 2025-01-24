package com.teamso.flight_reservation_system.dto.request;

import lombok.Data;

@Data
public class AirportRequestDto {
    private String name;
    private String code;
    private String city;
    private String country;
}
