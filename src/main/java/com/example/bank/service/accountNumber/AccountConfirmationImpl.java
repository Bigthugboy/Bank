package com.example.bank.service.accountNumber;

import com.example.bank.data.model.User;
import com.example.bank.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
@AllArgsConstructor
public class AccountConfirmationImpl implements AccountConfirmation {

    private final UserRepository userRepository;

    public void saveAccountConfirmation(User user) {
        userRepository.save(user);
    }

    public Optional<User> getNumber(User accountNumber) {
        return userRepository.findByAccountNumber(String.valueOf(accountNumber));}

    public void setConfirmedAt(User accountNumber) {
        Optional<User> user = (getNumber(accountNumber) );
        user.ifPresent(value -> value.setComfirmAt(LocalDateTime.parse(String.valueOf(LocalDateTime.now()))));
       userRepository.save(user.get());
    }
}
