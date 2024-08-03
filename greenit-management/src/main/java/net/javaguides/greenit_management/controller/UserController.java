package net.javaguides.greenit_management.controller;

import net.javaguides.greenit_management.dto.LoginDto;
import net.javaguides.greenit_management.dto.UserDto;
import net.javaguides.greenit_management.exception.UserAlreadyExistException;
import net.javaguides.greenit_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDTO) {
        try {
            UserDto savedUser = userService.saveUser(userDTO);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDTO = userService.getUserById(id);
        if (userDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginDto loginDTO) {
        try {
            UserDto userDTO = userService.loginUser(loginDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }
    // Other endpoints can be added here
}