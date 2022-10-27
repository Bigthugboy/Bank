package com.example.bank.sms;

import com.twilio.Twilio;
import com.twilio.http.HttpMethod;
import com.twilio.rest.api.v2010.account.Application;

import java.net.URI;

public class VoiceSms {
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Application application = Application.creator()
                .setVoiceMethod(HttpMethod.GET)
                .setVoiceUrl(URI.create("http://demo.twilio.com/docs/voice.xml"))
                .setFriendlyName("Phone Me")
                .create();

        System.out.println(application.getSid());
    }
}
