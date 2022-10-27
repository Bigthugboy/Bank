package com.example.bank.dto.request;

import com.example.bank.data.model.AccountType;
import com.example.bank.data.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserRequest {
 private String firstName;
 private String lastName;
 private String email;
 private String password;
 private String phoneNumber;
 private String username;
 private Gender gender;
 private String nextOfKin;
 private String address;
 private AccountType accountType;
 private int age;
}
