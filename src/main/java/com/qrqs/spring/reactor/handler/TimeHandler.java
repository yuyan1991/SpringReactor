package com.qrqs.spring.reactor.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@Slf4j
public class TimeHandler {
    public Mono<ServerResponse> getDateTime(ServerRequest request) {
        log.info("Enter into getDateTime(request :: {})", request);
        return ok().contentType(MediaType.TEXT_PLAIN).body(
                Mono.just("Now :: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                , String.class);
    }

    public Mono<ServerResponse> getDate(ServerRequest request) {
        log.info("Enter into getDate(request :: {})", request);
        return ok().contentType(MediaType.TEXT_PLAIN).body(
                Mono.just("Today :: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                , String.class);
    }

    public Mono<ServerResponse> getTimes(ServerRequest request) {
        log.info("Enter into getTimes(request :: {})", request);
        return ok().contentType(MediaType.TEXT_EVENT_STREAM).body(
                Flux.interval(Duration.ofSeconds(1))
                    .map(l -> {
                        log.info("l :: {}", l);
                        return new SimpleDateFormat("YYYY-MM-DD HH:mm:ss").format(new Date());
                    })
                    , String.class);
    }
}
