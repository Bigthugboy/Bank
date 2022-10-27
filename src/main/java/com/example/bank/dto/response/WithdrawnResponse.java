package com.example.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WithdrawnResponse {
    private String message;
    private String dateWithdrawn;
    private String balance;
}
