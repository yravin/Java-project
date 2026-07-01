
package com.learnapi.test.controller;

import com.learnapi.test.dto.TodoRequest;

import com.learnapi.test.dto.TodoResponse;


import com.learnapi.test.service.TodoService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j

@RestController

@RequestMapping("/todo")

public class TodoController {

    @Autowired

    private TodoService todoService;

    @PostMapping

    public ResponseEntity<Object> createTodo(@RequestBody TodoRequest todoRequest) {

        log.info("Create todo with request: {}", todoRequest);

        todoService.create(todoRequest);

        return ResponseEntity.ok().build();
    }



    @GetMapping

    public ResponseEntity<List<TodoResponse>> getAllTodos() {

        log.info("Get all todos");

        List<TodoResponse> todos = todoService.getAll();

        return ResponseEntity.ok(todos);
    }



    @GetMapping("/{id}")

    public ResponseEntity<TodoResponse> getTodoById(@PathVariable Long id) {

        log.info("Get todo by id: {}", id);



        TodoResponse todoResponse = todoService.getById(id);

        return ResponseEntity.ok(todoResponse);
    }



    @PutMapping("/{id}")

    public ResponseEntity<Object> updateTodo(

            @PathVariable Long id,

            @RequestBody TodoRequest todoRequest) {

        log.info("Update todo id: {} with request: {}", id, todoRequest);

        todoService.update(id, todoRequest);

        return ResponseEntity.ok().build();
    }



    @DeleteMapping("/{id}")

    public ResponseEntity<Object> deleteTodo(

            @PathVariable Long id,

            @RequestBody TodoRequest todoRequest) {

        log.info("Delete todo id: {}", id);

        todoService.delete(id, todoRequest);

        return ResponseEntity.ok().build();
    }
}