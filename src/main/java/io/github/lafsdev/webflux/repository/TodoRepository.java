package io.github.lafsdev.webflux.repository;

import io.github.lafsdev.webflux.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
}
