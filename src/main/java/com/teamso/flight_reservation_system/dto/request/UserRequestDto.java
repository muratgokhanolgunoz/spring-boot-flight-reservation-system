package com.teamso.flight_reservation_system.dto.request;

import lombok.Data;

@Data
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
}
