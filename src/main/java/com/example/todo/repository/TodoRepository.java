// Write your code here
package com.example.todo.repository;

import java.util.*;
import com.example.todo.model.*;

public interface TodoRepository {
    ArrayList<Todo> todolist();

    Todo add(Todo todo);

    Todo get(int id);

    Todo update(int id, Todo todo);

    void delete(int id);
}