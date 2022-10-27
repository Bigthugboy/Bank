package com.example.bank.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class AccountStatement {
    BigDecimal currentBalance;
    List<Transaction> transactionHistory;

    public AccountStatement(BigDecimal balance, List<Transaction> byAccountNumber) {


    }
}
