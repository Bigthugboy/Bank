package com.example.bank.service;

import com.example.bank.data.model.Transaction;
import com.example.bank.data.model.User;
import com.example.bank.dto.request.*;
import com.example.bank.dto.response.*;

import java.util.List;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest) ;
    DepositResponse deposit (DepositRequest request);
    WithdrawnResponse withdrawn (WithdrawnRequest request);
    pinResponse pin(PinRequest request);
    TransferResponse transfer(TransferRequest request);
    BalanceResponse checkBalance(BalanceRequest request);
    List<User> findAll();
    AccountStatementResponse getStatement(String accountNumber);
    DeleteResponse delete(DeleteRequest deleteRequest);
    User findByAccountNumber(FindRequest request);
    List<Transaction> findAllTransactions();
    AccountStatementResponse findByTransactionDate(SearchRequest request);


}
