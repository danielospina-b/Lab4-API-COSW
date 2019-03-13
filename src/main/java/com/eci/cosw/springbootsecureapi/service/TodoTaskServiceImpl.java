package com.eci.cosw.springbootsecureapi.service;

import java.util.ArrayList;
import java.util.List;
import com.eci.cosw.springbootsecureapi.model.TodoTask;
import org.springframework.stereotype.Service;

@Service
public class TodoTaskServiceImpl implements TodoTaskService {
    
    private List<TodoTask> tasks = new ArrayList<>();

    @Override
    public List<TodoTask> getTodoList() {
        return tasks;
    }

    @Override
    public void addTodo(TodoTask todo) {
        tasks.add(todo);
        System.out.println(todo.getDescription());
        System.out.println(todo.getStatus());
        System.out.println(todo.getDueDate());
        System.out.println(todo.getResponsible().toString());
    }
}