package io.github.lafsdev.webflux.service;

import io.github.lafsdev.webflux.model.Todo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface TodoService {
    Mono<Todo> save(Todo todo);
}
