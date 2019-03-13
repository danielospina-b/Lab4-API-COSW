package com.eci.cosw.springbootsecureapi.controller;

import java.util.List;

import com.eci.cosw.springbootsecureapi.model.TodoTask;
import com.eci.cosw.springbootsecureapi.service.TodoTaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TodoTaskController {

    @Autowired
    private TodoTaskService todoTaskService;

    @RequestMapping("/api")
    public List<TodoTask> getAllTodos() {
        return todoTaskService.getTodoList();
    }

    @RequestMapping(value="/api", method=RequestMethod.POST)
    public void addNewTodo(@RequestBody TodoTask todo) {
        todoTaskService.addTodo(todo);
    }
}