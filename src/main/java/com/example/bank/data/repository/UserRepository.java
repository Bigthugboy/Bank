package com.example.bank.data.repository;

import com.example.bank.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    Optional<User> findByAccountNumber(String accountNumber);
}
