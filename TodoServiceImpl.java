package com.learnapi.test.service.impl;
import com.learnapi.test.dto.TodoRequest;
import com.learnapi.test.dto.TodoResponse;
import com.learnapi.test.model.Todo;
import com.learnapi.test.repository.TodoRepository;
import com.learnapi.test.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class TodoServiceImpl  implements TodoService {

   private  final  TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void create(TodoRequest todoRequest) {
        Todo todo = new Todo();
        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setCompleted(todoRequest.getCompleted());
        todoRepository.save(todo);
    }

    @Override
    public void update(Long id, TodoRequest todoRequest) {
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isEmpty()){
            log.info("information not found");
            return;
        }
       Todo todoToUpdate = todo.get();
        todoToUpdate.setTitle(todoRequest.getTitle());
        todoToUpdate.setDescription(todoRequest.getDescription());
        todoToUpdate.setCompleted(todoRequest.getCompleted());
        todoRepository.saveAndFlush(todoToUpdate);
    }

    @Override
    public void delete(Long id, TodoRequest todoRequest) {
    todoRepository.deleteById(id);
    }

    @Override
    public TodoResponse getById(Long id) {
       TodoResponse todoResponse = new TodoResponse();
       Optional<Todo> todo = todoRepository.findById(id);
       if(todo.isEmpty()){
           log.info("imformation not found");
           return todoResponse;
       }
       todoResponse.setId(todo.get().getId());
       todoResponse.setTitle(todo.get().getTitle());
       todoResponse.setDescription(todo.get().getDescription());
       todoResponse.setCompleted(todo.get().getCompleted());
       return todoResponse;
    }

    @Override
    public List<TodoResponse> getAll() {
        List<TodoResponse> responseList = new ArrayList<>();
        List<Todo> todo = todoRepository.findAll();

        if(todo.isEmpty()){
            log.info("not to do found");
            return responseList;
        }

        for(Todo t : todo){
            TodoResponse dto = new TodoResponse();
            dto.setId(t.getId());
            dto.setTitle(t.getTitle());
            dto.setDescription(t.getDescription());
            dto.setCompleted(t.getCompleted());
            responseList.add(dto);
        }

        return responseList;
    }
}
