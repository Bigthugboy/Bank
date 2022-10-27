package com.example.bank.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Transaction {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private BigDecimal transactionAmount;

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private TransactionDetails transactionDetails;

    private String date;

    private String firstName;

    private String LastName;

    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    private User users;

}
