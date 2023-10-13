package com.geybriyel.todomvc.controller;

import com.geybriyel.todomvc.entity.Todo;
import com.geybriyel.todomvc.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/todo")
public class TodoController {

    private TodoService todoService;

    @GetMapping("/all-todos-admin")
    public String showAllTodo(ModelMap modelMap) {
        List<Todo> todos = todoService.getAllTodos();
        modelMap.put("todos", todos);
        return "todoList";
    }

    @GetMapping("/all-todos")
    public String showAllTodoByUsername(ModelMap modelMap) {
        String username = getUsername();
        List<Todo> todos = todoService.getTodoByUsername(username);
        modelMap.put("todos", todos);
        modelMap.put("name", username);
        return "todoList";
    }

    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/add-todo")
    public String showAddTodoPage(ModelMap modelMap) {
        String username = getUsername();
        Todo todo = Todo.builder()
                .username(username)
                .description("")
                .targetDate(LocalDate.now())
                .done(false)
                .build();
        modelMap.put("todo", todo);
        return "todo";
    }

    @PostMapping("/add-todo")
    public String addNewTodo(Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        todo.setUsername(getUsername());
        todoService.addNewTodo(todo);
        return "redirect:all-todos";
    }

    @GetMapping("/edit")
    public String showEditPage(@RequestParam Long id, ModelMap modelMap) {
        Todo todo = todoService.getTodoById(id);
        modelMap.put("todo", todo);
        return "todo";
    }

    @PostMapping("/edit")
    public String editTodo(Todo todo) {
        todo.setUsername(getUsername());
        todoService.updateTodo(todo);
        return "redirect:all-todos";
    }

    @GetMapping("/delete")
    public String deleteTodo(@RequestParam Long id) {
        todoService.deleteTodo(id);
        return "redirect:all-todos";
    }

}
