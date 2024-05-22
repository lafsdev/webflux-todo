package io.github.lafsdev.webflux.service;

import io.github.lafsdev.webflux.model.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface TodoService {
    Mono<Todo> save(Todo todo);

    Mono<Todo> findById(Long id);

    Flux<Todo> findAll();

    Mono<ResponseEntity<Void>> remove(Long id);
}
