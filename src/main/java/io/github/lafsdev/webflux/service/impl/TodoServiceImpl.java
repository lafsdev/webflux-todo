package io.github.lafsdev.webflux.service.impl;

import io.github.lafsdev.webflux.model.Todo;
import io.github.lafsdev.webflux.repository.TodoRepository;
import io.github.lafsdev.webflux.service.TodoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;


@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    private final TransactionTemplate transactionTemplate;

    @Qualifier("jdbcScheduler")
    private final Scheduler jdbcScheduler;

    public TodoServiceImpl(TodoRepository todoRepository, TransactionTemplate transactionTemplate, Scheduler jdbcScheduler) {
        this.todoRepository = todoRepository;
        this.transactionTemplate = transactionTemplate;
        this.jdbcScheduler = jdbcScheduler;
    }

    @Override
    public Mono<Todo> save(Todo todo) {
        Mono op = Mono.fromCallable(() -> this.transactionTemplate.execute(action -> {
            Todo newTodo = this.todoRepository.save(todo);
            return newTodo;
        }));
        return op;
    }

    @Override
    public Mono<Todo> findById(Long id) {
        return Mono.justOrEmpty(this.todoRepository.findById(id));
    }

    @Override
    public Flux<Todo> findAll() {
        return Flux.defer(() -> Flux.fromIterable(this.todoRepository.findAll()).subscribeOn(jdbcScheduler));
    }

    @Override
    public Mono<ResponseEntity<Void>> remove(Long id) {
        return Mono.fromCallable(() -> this.transactionTemplate.execute(actions -> {
            this.todoRepository.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        })).subscribeOn(jdbcScheduler);
    }
}
