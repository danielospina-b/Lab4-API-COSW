package com.eci.cosw.springbootsecureapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import com.eci.cosw.springbootsecureapi.model.TodoTask;
import org.springframework.stereotype.Service;

@Service
public class TodoTaskServiceImpl implements TodoTaskService {
    
    private List<TodoTask> tasks = new ArrayList<>();

    @PostConstruct
    private void populateSampleData() {
        HashMap<String,String> responsible1 = new HashMap<>();
        responsible1.put("email", "daniel@mail.com");
        responsible1.put("name", "Daniel");
        tasks.add(new TodoTask("Comprar Repuestos", "Ready", "1554126456114", responsible1));
    }

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