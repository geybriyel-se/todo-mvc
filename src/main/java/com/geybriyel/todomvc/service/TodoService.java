package com.geybriyel.todomvc.service;

import com.geybriyel.todomvc.entity.Todo;
import com.geybriyel.todomvc.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public List<Todo> getTodoByUsername(String username) {
        return todoRepository.findByUsername(username);
    }

    public void addNewTodo(Todo todo) {
        todoRepository.save(todo);
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).get();
    }

    public void updateTodo(Todo todo) {
        todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        Todo todo = getTodoById(id);
        todoRepository.delete(todo);
    }

}
