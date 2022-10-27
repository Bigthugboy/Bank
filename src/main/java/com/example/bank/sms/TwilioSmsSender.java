package com.example.bank.sms;

//import com.example.bank.config.TwilioConfig;
import com.example.bank.dto.request.SmsRequest;
import com.example.bank.exceptions.ErrorException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;

import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("twilio")
public class TwilioSmsSender implements SmsSender {
//    private final TwilioConfig twilioConfig;

//    @Autowired
//    public TwilioSmsSender(TwilioConfig twilioConfig) {
//        this.twilioConfig = twilioConfig;
//    }

    @Override
    public void sendSms(SmsRequest request) {
        final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
        final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");


            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber("+18646424452"),
                            new com.twilio.type.PhoneNumber("+18646424452"),
                            "Where's Wallace?")
                    .create();

            System.out.println(message.getSid());
        }
    }

//        Twilio.init("ACbacfc90de09e68295ab30f53fcac3985","aceb5798b70ca5d0aa9af15616fc5287");
//        if (isPhoneNumberValid(request.getPhoneNumber())) {
//            PhoneNumber to = new PhoneNumber(request.getPhoneNumber());
//            PhoneNumber from = new PhoneNumber("+18646424452");
//            String message = request.getMessage();
//            MessageCreator creator = Message.creator(to, from, message);
//            creator.create();
//            log.info("Send sms {}" + request);
//        } else {
//            throw new ErrorException("phone number {" + request.getPhoneNumber() + "} is not valid number");
//        }
//    }
//    private boolean isPhoneNumberValid(String phoneNumber){
//        return true;
//    }

