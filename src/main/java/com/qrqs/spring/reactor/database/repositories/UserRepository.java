package com.qrqs.spring.reactor.database.repositories;

import com.qrqs.spring.reactor.database.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, Long> {
    Mono<User> findByUsername(String username);
    Mono<Long> deleteByUsername(String username);
}
