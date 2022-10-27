package com.example.bank.service;

import com.example.bank.data.model.Transaction;
import com.example.bank.data.model.TransactionDetails;
import com.example.bank.data.model.User;
import com.example.bank.data.repository.TransactionRepository;
import com.example.bank.data.repository.UserRepository;
import com.example.bank.dto.request.*;
import com.example.bank.dto.response.*;
import com.example.bank.exceptions.DuplicateEmailException;
import com.example.bank.exceptions.ErrorException;
import com.example.bank.exceptions.InsufficientFundExceptions;
import com.example.bank.exceptions.InvalidPinException;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    private final TransactionRepository transactionRepository;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserResponse registerUser(UserRequest userRequest) {
       if(findByMail(userRequest.getEmail())){
            throw new DuplicateEmailException("ACCOUNT ALREADY REQUEST");
        }
        User user;
        ModelMapper modelMapper = new ModelMapper();
        user = modelMapper.map(userRequest, User.class);
        String encodedPassword = bCryptPasswordEncoder.encode(userRequest.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        String accountNumber = String.valueOf(UUID.randomUUID().getLeastSignificantBits()).substring(1,11);
         user.setAccountNumber(accountNumber);
        log.info("ok ok -> {}", user.getAccountNumber());
        User saveUser = userRepository.save(user);
        UserResponse response = new UserResponse();
         response.setDateCreated(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy, hh:mm, a").format(saveUser.getDate()));
        response.setEmail(saveUser.getEmail());
        response.setUsername(saveUser.getUsername());
        response.setMessage("account successfully created");
        response.setAccountNumber(saveUser.getAccountNumber());
        response.setPin("0000");
        return response;
    }

    @Override
    public pinResponse pin(PinRequest request) {
        Optional<User> user = userRepository.findByAccountNumber(request.getAccountNumber());
        if (user.isPresent()){
            String encodePin = bCryptPasswordEncoder.encode(request.getPin());
            user.get().setPin(encodePin);
            userRepository.save(user.get());
            pinResponse response = new pinResponse();
            response.setMessage("YOUR NEW PIN IS " + request.getPin());
            return response;
        }
      throw new ErrorException("USER DOES NOT EXIST");
    }
    @Transactional
    @Override
    public DepositResponse deposit(DepositRequest request) {
        Optional<User> user = userRepository.findByAccountNumber(request.getAccountNumber());
        if (user.isPresent()){
            user.get().setBalance(request.getAmount().add(user.get().getBalance()));
            User savedDeposit = userRepository.save(user.get());

            Transaction transaction = new Transaction();
            transaction.setAccountNumber(request.getAccountNumber());
            transaction.setTransactionAmount(request.getAmount());
            transaction.setFirstName(user.get().getFirstName());
            transaction.setLastName(user.get().getLastName());
            transaction.setBalance(user.get().getBalance());


           transaction.setDate(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy, hh:mm, a").format(savedDeposit.getDate()));

            transaction.setTransactionDetails(TransactionDetails.DEPOSIT);
            transactionRepository.save(transaction);

            DepositResponse response = new DepositResponse();
            response.setFirstName(savedDeposit.getFirstName());
            response.setLastName(savedDeposit.getLastName());
            response.setMessage("SUCCESSFULLY DEPOSITED");
            response.setBalance(String.valueOf(user.get().getBalance()));

            response.setDateTime(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy, hh:mm, a").format(savedDeposit.getDate()));
            return response;
        }
       throw new ErrorException("USER DOES NOT EXIST");
    }
    @Transactional
    @Override
    public WithdrawnResponse withdrawn(WithdrawnRequest request) {

        Optional<User> user = (userRepository.findByAccountNumber(request.getAccountNumber()));


        if (user.isPresent()){

            if(bCryptPasswordEncoder.matches(request.getPin(), user.get().getPin())){
                if(request.getAmount().compareTo(user.get().getBalance()) < 1) {
                    if (request.getAmount().compareTo(BigDecimal.ONE) > 0){
                        user.get().setBalance(user.get().getBalance().subtract(request.getAmount()));
                        User savedWithdrawn = userRepository.save(user.get());


                        Transaction transaction = new Transaction();
                        transaction.setTransactionDetails(TransactionDetails.WITHDRAWN);
                        transaction.setTransactionAmount(request.getAmount());
                        transaction.setAccountNumber(request.getAccountNumber());
                        transaction.setFirstName(user.get().getFirstName());
                        transaction.setLastName(user.get().getLastName());
                         transaction.setBalance(user.get().getBalance());
                        transaction.setId(transaction.getId());
                        transactionRepository.save(transaction);

                        WithdrawnResponse withdrawnResponse = new WithdrawnResponse();
                        withdrawnResponse.setMessage("SUCCESSFULLY WITHDRAWN");
                        withdrawnResponse.setBalance(String.valueOf(user.get().getBalance()));
                        withdrawnResponse.setDateWithdrawn(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy, hh:mm, a").format(savedWithdrawn.getDate()));
                        return withdrawnResponse;
                    }
                    throw new ErrorException("Cant withdrawn a negative amount");
                }
                throw new InsufficientFundExceptions("Insufficient balance");
            }
            throw new InvalidPinException("INCORRECT PIN");
        }
      throw new ErrorException("USER DOES NOT EXIST");
    }


    @Transactional
    @Override
    public TransferResponse transfer(TransferRequest transferRequest) {
        User user = new User();
        WithdrawnRequest request = new WithdrawnRequest();
        request.setAmount(transferRequest.getAmount());
        request.setAccountNumber(transferRequest.getFromAccount());
        request.setPin(transferRequest.getPin());

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount(transferRequest.getAmount());
        depositRequest.setAccountNumber(transferRequest.getToAccount());

        Transaction transaction = new Transaction();
        transaction.setTransactionDetails(TransactionDetails.TRANSFER);

        transaction.setTransactionAmount(transferRequest.getAmount());

        withdrawn(request);
        deposit(depositRequest);

        TransferResponse response = new TransferResponse();
        response.setAmount(request.getAmount());
        response.setBalance(user.getBalance());
        response.setAccountNumber(request.getAccountNumber());
        response.setTransactionId(transaction.getId());
//        transaction.setDate(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy, hh:mm, a").format(response.getDate()));

        response.setMessage("YOU HAVE SUCCESSFULLY TRANSFER "  + request.getAmount()  + " TO " + depositRequest.getAccountNumber());

        return response;

    }

    @Override
    public BalanceResponse checkBalance(BalanceRequest request) {

        Optional<User> user = userRepository.findByAccountNumber(request.getAccountNumber());
        if(user.isPresent()){
            if(bCryptPasswordEncoder.matches(request.getPin(), user.get().getPin())){
                BalanceResponse response = new BalanceResponse();
                response.setBalance(user.get().getBalance());
                return response;
            }
            throw new InvalidPinException("INCORRECT PIN");
        }
        throw new InvalidPinException("USER DOES NOT EXIST");
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AccountStatementResponse getStatement(String accountNumber) {
        User user = new User();
//        if (user.getAccountNumber().equals(transactionRepository.findByAccountNumber(accountNumber))) {
        List<Transaction> transactions = transactionRepository.findByAccountNumber(accountNumber);
        System.out.println(transactions.size());
        AccountStatementResponse response = new AccountStatementResponse();
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setBalance(user.getBalance());
            response.setTransactionList(transactions);
            return response;
//        }

    }

    @Override
    public DeleteResponse delete(DeleteRequest deleteRequest) {
        Optional<User> user = userRepository.findByAccountNumber(deleteRequest.getAccountNumber());
        if(bCryptPasswordEncoder.matches(deleteRequest.getPin(), user.get().getPin())) {
            userRepository.delete(user.get());
            DeleteResponse response = new DeleteResponse();
            response.setAccountNumber(deleteRequest.getAccountNumber());
            response.setMessage("ACCOUNT SUCCESSFULLY DELETED  " + deleteRequest.getAccountNumber());
            return response;
        }
        throw new InvalidPinException("INCORRECT PIN");
    }

    @Override
    public User findByAccountNumber(FindRequest request) {
        Optional<User> optionalUser =  userRepository.findByAccountNumber(request.getAccountNumber());
        System.out.println(request.getAccountNumber());
        if (optionalUser.isEmpty()){
            throw new ErrorException("NOT FOUND");
        }
        return optionalUser.get();

    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public AccountStatementResponse findByTransactionDate(SearchRequest request) {
        User user = new User();
        List<Transaction> transactions = transactionRepository.findByDateBetween(request.getFrom(),request.getTo());

            AccountStatementResponse response = new AccountStatementResponse();
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setBalance(user.getBalance());
            response.setTransactionList(transactions);
            return response;
        }



    private boolean findByMail(String existByMail){
     User user = userRepository.findByEmail(existByMail);
     return user != null;

    }

}
