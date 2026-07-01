package com.learnapi.test.service;

import com.learnapi.test.dto.TodoRequest;
import com.learnapi.test.dto.TodoResponse;

import java.util.List;

public interface TodoService {
    void create(TodoRequest todoRequest);
    void update(Long id,TodoRequest todoRequest);
    void delete(Long id, TodoRequest todoRequest);
    TodoResponse getById(Long id);
    List<TodoResponse>getAll();
}

//TodoService (Interface): គ្រាន់តែជាអ្នកប្រកាស កិច្ចសន្យា (Contract) ឬ
// បញ្ជីមុខងារថាតើ Service នេះអាចធ្វើអ្វីបានខ្លះ ប៉ុន្តែវាមិនដឹងថាត្រូវធ្វើដោយរបៀបណាទេ។
//អ្នកដែលធ្វេីការលេីLogicគឺ TodoServiceImpl
