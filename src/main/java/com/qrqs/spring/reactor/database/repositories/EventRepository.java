package com.qrqs.spring.reactor.database.repositories;

import com.qrqs.spring.reactor.database.model.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface EventRepository extends ReactiveMongoRepository<Event, Long> {
    @Tailable
    Flux<Event> findBy();
}
