package com.qrqs.spring.reactor.controller;

import com.qrqs.spring.reactor.database.model.User;
import com.qrqs.spring.reactor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SuppressWarnings({"unused"})
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public Mono<User> getUser(@PathVariable String username) {
        log.info("Enter into getUser(username :: {})", username);
        return userService.findByUserName(username);
    }

    @DeleteMapping("/{username}")
    public Mono<Long> deleteUser(@PathVariable String username) {
        log.info("Enter into deleteUser(username :: {})", username);
        return userService.deleteByUserName(username);
    }

    @GetMapping("")
    public Flux<User> findAllUser() {
        log.info("Enter into findAllUser()");
        return userService.findAll();
    }

    @PostMapping("")
    public Mono<User> saveUser(User user) {
        log.info("Enter into saveUser(user)", user);
        return userService.save(user);
    }
}
