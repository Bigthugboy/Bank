package com.example.bank.data.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;


import javax.persistence.*;
import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;

    private String lastName;


    private String address;

    private String email;

    private String phoneNumber;
    private BigDecimal balance = BigDecimal.valueOf(0);

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int age;

    private String nextOfKin;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;



    private Boolean locked = false;

    private Boolean enabled = true;

    private Boolean isSuspended = false;

    private LocalDateTime date = LocalDateTime.now();


    private String password;

    private String username;

    private String accountNumber;

    private LocalDateTime comfirmAt;

    private String pin;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Transaction> transaction;


}
