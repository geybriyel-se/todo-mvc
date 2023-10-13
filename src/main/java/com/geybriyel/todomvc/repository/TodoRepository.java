package com.geybriyel.todomvc.repository;

import com.geybriyel.todomvc.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUsername(String username);
}
