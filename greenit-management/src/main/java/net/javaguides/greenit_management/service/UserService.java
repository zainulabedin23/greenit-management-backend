package net.javaguides.greenit_management.service;

import net.javaguides.greenit_management.dto.LoginDto;
import net.javaguides.greenit_management.dto.UserDto;
import net.javaguides.greenit_management.entity.User;
import net.javaguides.greenit_management.exception.UserAlreadyExistException;
import net.javaguides.greenit_management.mapper.UserMapper;
import net.javaguides.greenit_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDto saveUser(UserDto userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyExistException("User with email " + userDTO.getEmail() + " already exists.");
        }
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toDTO).orElse(null);
    }
    public UserDto loginUser(LoginDto loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!loginDTO.getPassword().equals(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        return userMapper.toDTO(user);
    }

    // Other CRUD methods can be added here
}
