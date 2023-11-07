/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 */
package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.todo.service.*;
import com.example.todo.model.*;

// Write your code here
@RestController
public class TodoController {
    @Autowired
    public TodoH2Service ts;

    @GetMapping("/todos")
    public ArrayList<Todo> api1() {
        return ts.todolist();
    }

    @PostMapping("/todos")
    public Todo api2(@RequestBody Todo todo) {
        return ts.add(todo);
    }

    @GetMapping("/todos/{id}")
    public Todo api3(@PathVariable("id") int id) {
        return ts.get(id);
    }

    @PutMapping("todos/{id}")
    public Todo api4(@PathVariable("id") int id, @RequestBody Todo todo) {
        return ts.update(id, todo);
    }

    @DeleteMapping("todos/{id}")
    public void api5(@PathVariable("id") int id) {
        ts.delete(id);
    }
}
