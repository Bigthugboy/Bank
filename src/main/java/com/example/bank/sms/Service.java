package com.example.bank.sms;

import com.example.bank.dto.request.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
public class Service {
    private final TwilioSmsSender twilioSmsSender;

    @Autowired
    public Service(@Qualifier("twilio") TwilioSmsSender twilioSmsSender){
        this.twilioSmsSender = twilioSmsSender;
    }
    public void sendSms(SmsRequest request){
        twilioSmsSender.sendSms(request);
    }

}
