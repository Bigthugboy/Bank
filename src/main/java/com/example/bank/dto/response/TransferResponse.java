package com.example.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.temporal.TemporalAccessor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferResponse {
    private BigDecimal amount;
    private BigDecimal balance;
    private String accountNumber;
    private String message;
    private Long transactionId;
    private String dateTransfer;
}
