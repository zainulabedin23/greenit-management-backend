package net.javaguides.greenit_management.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDto {
    private Long userId;
    private String userName;
    private String email;
    private String password;
    private String role;
    private String fullName;
    private Timestamp createdAt;
    private Timestamp updatedAt;


}