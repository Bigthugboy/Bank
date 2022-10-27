package com.example.bank.exceptions;

public class BankExceptions extends RuntimeException {
    private int statusCode;


    public BankExceptions(String message) {
        super(message);
        this.statusCode = statusCode;

    }
    public int getStatusCode(){return  statusCode;}
}
