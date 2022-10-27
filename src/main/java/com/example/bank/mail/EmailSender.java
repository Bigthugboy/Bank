package com.example.bank.mail;


import com.example.bank.dto.request.VerificationMessageRequest;
import com.example.bank.dto.response.MailResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.concurrent.CompletableFuture;

public interface EmailSender {
    CompletableFuture<MailResponse> sendHtmlMail(VerificationMessageRequest messageRequest)throws UnirestException; ;
    CompletableFuture<MailResponse> sendSimpleMail(VerificationMessageRequest messageRequest)throws UnirestException;

}
