package io.github.lafsdev.webflux.controller;

import io.github.lafsdev.webflux.model.Todo;
import io.github.lafsdev.webflux.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/todos")
public class TodoController {


    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @PostMapping
    public Mono<Todo> save(@RequestBody Todo todo) {
        return this.todoService.save(todo);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Mono<Todo> findById(@PathVariable("id") Long id) {
        return this.todoService.findById(id);
    }

    @GetMapping
    @ResponseBody
    public Flux<Todo> findAll() {
        return this.todoService.findAll();
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> remove(@PathVariable("id") Long id) {
        return this.todoService.remove(id);
    }

}
