package com.example.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepositResponse {
    private String message;
    private String dateTime;
    private String firstName;
    private String lastName;
    private String balance;


}
