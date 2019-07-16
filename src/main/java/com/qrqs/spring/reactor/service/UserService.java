package com.qrqs.spring.reactor.service;

import com.qrqs.spring.reactor.database.model.User;
import com.qrqs.spring.reactor.database.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SuppressWarnings({"unused"})
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Mono<User> save(User user) {
        return userRepository.save(user)
                .onErrorResume(e ->
                    userRepository.findByUsername(user.getUsername())
                            .flatMap(existedUser -> {
                                user.setId(existedUser.getId());
                                return userRepository.save(user);
                            })
                );
    }

    public Mono<User> findByUserName(String username) {
        return  userRepository.findByUsername(username);
    }

    public Mono<Long> deleteByUserName(String username) {
        return userRepository.deleteByUsername(username);
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }
}
