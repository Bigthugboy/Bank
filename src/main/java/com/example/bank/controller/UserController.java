package com.example.bank.controller;


import com.example.bank.dto.request.*;
import com.example.bank.dto.response.ApiResponse;
import com.example.bank.exceptions.BankExceptions;
import com.example.bank.exceptions.DuplicateEmailException;
import com.example.bank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody UserRequest request) {
        try {
            var serviceResponse = userService.registerUser(request);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DuplicateEmailException ex) {
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositRequest request) {
        try {
            var serviceResponse = userService.deposit(request);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (BankExceptions e) {
            ApiResponse response = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/withdrawn")
    public ResponseEntity<?> withdrawn(@RequestBody WithdrawnRequest request) {
        try {
            var serviceResponse = userService.withdrawn(request);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BankExceptions ex) {
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/transfer")
    public ResponseEntity<?> transfer (@RequestBody TransferRequest request) {
        try {
            var serviceResponse = userService.transfer(request);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BankExceptions ex) {
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/pin")
    public ResponseEntity<?> pin (@RequestBody PinRequest request){
        try {
            var serviceResponse = userService.pin(request);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BankExceptions ex) {
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/balance")
    public ResponseEntity<?> balance (@RequestBody BalanceRequest request){
        try {
            var serviceResponse = userService.checkBalance(request);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BankExceptions ex) {
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/delete")
    public ResponseEntity<?> delete (@RequestBody DeleteRequest request){
        try {
            var serviceResponse = userService.delete(request);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BankExceptions ex) {
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/find")
    public ResponseEntity<?> find (){
        try {
            var serviceResponse = userService.findAll();
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BankExceptions ex) {
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/findAll")
    public ResponseEntity<?> findAll (){
        try {
            var serviceResponse = userService.findAllTransactions();
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BankExceptions ex) {
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/statement/{accountNumber}")
    public ResponseEntity<?> statement( @PathVariable String accountNumber){
        try {
            var serviceResponse = userService.getStatement(accountNumber);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BankExceptions ex) {
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/findByAccountNumber")
    public ResponseEntity<?> findByAccountNumber (@RequestBody FindRequest request){
        try {
            var serviceResponse = userService.findByAccountNumber(request);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BankExceptions ex) {
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/findTransactionByDate")
    public ResponseEntity<?> findTransactionByDate (@RequestBody SearchRequest request){
        try {
            var serviceResponse = userService.findByTransactionDate(request);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BankExceptions ex) {
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }



//    @GetMapping("/findBy/{accountNumber}")
//    public AccountStatementResponse findBy( @PathVariable String accountNumber){return userService.findByTransactionDate(accountNumber);}




}
//