package com.geybriyel.todomvc.controller;

import com.geybriyel.todomvc.entity.Todo;
import com.geybriyel.todomvc.service.TodoService;
import jakarta.persistence.PostRemove;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/todo")
public class TodoController {

    private TodoService todoService;

    @GetMapping("/")
    public String showHome() {
        log.info("Showing homepage");
        return "home";
    }

    @GetMapping("/all-todos")
    public String showAllTodo(ModelMap modelMap) {
        List<Todo> todos = todoService.getAllTodos();
        modelMap.put("todos", todos);
        return "todoList";
    }

    @GetMapping("/add-todo")
    public String showAddTodoPage(ModelMap modelMap) {
        Todo todo = Todo.builder()
                .username("")
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
        todoService.updateTodo(todo);
        return "redirect:all-todos";
    }

    // TODO: 10/13/23 replace with post after adding 'active' field
    @GetMapping("/delete")
    public String deleteTodo(@RequestParam Long id) {
        todoService.deleteTodo(id);
        return "redirect:all-todos";
    }

}
