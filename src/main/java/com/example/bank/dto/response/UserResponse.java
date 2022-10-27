package com.example.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {
    private String message;
    private String username;
    private String email;
    private String dateCreated;
    private String accountNumber;
    private String pin;
}
