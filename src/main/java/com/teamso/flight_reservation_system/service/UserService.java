package com.teamso.flight_reservation_system.service;

import com.teamso.flight_reservation_system.dto.UserDto;
import com.teamso.flight_reservation_system.entity.User;
import com.teamso.flight_reservation_system.repository.UserRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User create(UserDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFistName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();

        return userRepository.save(user);
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
}
