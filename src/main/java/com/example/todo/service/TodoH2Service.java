/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */
package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.todo.model.TodoRowMapper;
import com.example.todo.repository.TodoRepository;
import com.example.todo.model.*;

// Write your code here
@Service
public class TodoH2Service implements TodoRepository {
    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Todo> todolist() {
        List<Todo> l = db.query("select * from TODOLIST", new TodoRowMapper());
        ArrayList<Todo> al = new ArrayList<>(l);
        return al;
    }

    @Override
    public Todo add(Todo todo) {
        db.update("insert into TODOLIST(todo,status,priority) values(?,?,?)", todo.getTodo(),
                todo.getPriority(), todo.getStatus());
        Todo savedtodo = db.queryForObject("select * from TODOLIST where todo=? and priority=? and status=?",
                new TodoRowMapper(), todo.getTodo(), todo.getPriority(), todo.getStatus());
        return savedtodo;
    }

    @Override
    public Todo get(int id) {
        try {
            Todo t = db.queryForObject("select * from TODOLIST where id=?", new TodoRowMapper(), id);
            return t;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Todo update(int id, Todo todo) {
        try {
            if (todo.getStatus() != null) {
                db.update("update TODOLIST set status=? where id=?", todo.getStatus(), id);
            }
            return get(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void delete(int id) {
        try {
            db.update("delete from TODOLIST where id=?", id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}
