package com.example.bank.sms;


import com.example.bank.dto.request.SmsRequest;

public interface SmsSender {
    void sendSms(SmsRequest request);
}
