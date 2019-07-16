package com.qrqs.spring.reactor.controller;

import com.qrqs.spring.reactor.database.model.User;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class UserControllerTest {
    @Test
    public void webClientTest() throws InterruptedException {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
        webClient
                .get().uri("/user")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .flatMapMany(response -> response.bodyToFlux(User.class))
                .doOnNext(System.out::println)
                .blockLast();
    }
}
