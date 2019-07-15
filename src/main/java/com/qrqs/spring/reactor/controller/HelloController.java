package com.qrqs.spring.reactor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class HelloController {
    @GetMapping("/hello")
    public Mono<String> hello() {
        log.info("Enter into hello()");
        return Mono.just("Hello World!");
    }
}
