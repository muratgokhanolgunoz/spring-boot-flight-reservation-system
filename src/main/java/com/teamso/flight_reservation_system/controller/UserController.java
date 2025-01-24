package com.teamso.flight_reservation_system.controller;

import com.teamso.flight_reservation_system.dto.UserDto;
import com.teamso.flight_reservation_system.entity.User;
import com.teamso.flight_reservation_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }
}
