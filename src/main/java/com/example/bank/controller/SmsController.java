package com.example.bank.controller;



import com.example.bank.dto.request.SmsRequest;
import com.example.bank.sms.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/sms")
public class SmsController {
    private final Service service;

    @Autowired
    public SmsController(Service service){
        this.service= service;
    }
    @PostMapping
    public void sendSms(@Valid @RequestBody SmsRequest request){
        service.sendSms(request);
    }
}
