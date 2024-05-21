package io.github.lafsdev.webflux.controller;

import io.github.lafsdev.webflux.model.Todo;
import io.github.lafsdev.webflux.repository.TodoRepository;
import io.github.lafsdev.webflux.service.TodoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/todos")
public class TodoController {


    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @PostMapping
    public Mono<Todo> save(@RequestBody Todo todo){
    return this.todoService.save(todo);
    }


}
