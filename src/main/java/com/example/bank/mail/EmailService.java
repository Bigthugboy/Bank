package com.example.bank.mail;

import com.example.bank.data.model.User;
import com.example.bank.dto.request.VerificationMessageRequest;
import com.example.bank.dto.response.MailResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
@Slf4j
@Service("mailgun_service")
@AllArgsConstructor
public class EmailService implements EmailSender {
    private final String DOMAIN = System.getenv("DOMAIN");
    private final String PRIVATE_KEY = System.getenv("MAILGUN_PRIVATE_KEY");
//

    @Override
    @Async
    public CompletableFuture<MailResponse> sendHtmlMail(VerificationMessageRequest messageRequest) throws UnirestException {
        HttpResponse<String> request = (HttpResponse<String>) Unirest.post("https://api.mailgun.net/v3/" + DOMAIN + "/message")
                .basicAuth("api", PRIVATE_KEY)
                .queryString("from", messageRequest.getSender())
                .queryString("to", messageRequest.getReceiver())
                .queryString("subject", messageRequest.getSubject())
                .queryString("html", messageRequest.getBody())
                .asString();

        log.info("Message sent successfully");
        MailResponse mailResponse = request.statusCode() == 200 ? new MailResponse(true) : new MailResponse(false);
        return CompletableFuture.completedFuture(mailResponse);
    }

    @Override
    @Async
    public CompletableFuture<MailResponse> sendSimpleMail(VerificationMessageRequest messageRequest) throws UnirestException {
       User user = new User();
        HttpResponse<JsonNode> request = (HttpResponse<JsonNode>) Unirest.post("https://api.mailgun.net/v3/" + DOMAIN + "/messages")
                .basicAuth("api", PRIVATE_KEY)
                .queryString("from", messageRequest.getSender())
                .queryString("to", messageRequest.getReceiver())
                .queryString("subject", messageRequest.getSubject())
                .queryString("text", messageRequest.getBody())
                .asJson();
        log.info("Message sent successfully");
        MailResponse mailResponse = request.statusCode() == 200 ? new MailResponse(true) : new MailResponse(false);
        return CompletableFuture.completedFuture(mailResponse);

    }
}
