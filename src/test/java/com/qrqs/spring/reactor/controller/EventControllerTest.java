package com.qrqs.spring.reactor.controller;

import com.qrqs.spring.reactor.database.model.Event;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class EventControllerTest {
    @Test
    public void webClientTest() {
        Flux<Event> eventFlux = Flux.interval(Duration.ofSeconds(1))
                .map(l -> new Event(System.currentTimeMillis(), "message-" + l)).take(5);
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient
                .post().uri("/events")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(eventFlux, Event.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
