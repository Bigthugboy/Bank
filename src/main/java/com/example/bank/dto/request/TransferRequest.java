package com.example.bank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferRequest {
    private BigDecimal amount;
    private String accountNumber;
    private String pin;
    private String fromAccount;
    private String toAccount;


}
