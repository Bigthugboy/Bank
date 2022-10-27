package com.example.bank.dto.response;

import com.example.bank.data.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountStatementResponse {
    private String FirstName;
    private String LastName;
    private BigDecimal balance;
    private List<Transaction> transactionList;
}
